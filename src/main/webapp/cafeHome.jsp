<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
	<!DOCTYPE html>
	<html lang="en">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>카페주문관리</title>
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	</head>

	<style>
		.div1 {
			background-color: rgb(255, 192, 203);
		}

		.div2 {
			background-color: rgb(205, 209, 255);
		}
	</style>

	<body>
		<table style="width: 100%; border-collapse: collapse;" class="div1">
			<tr>
				<td style="width: 33%; border: 1px solid black;; vertical-align: top;">
					<table style="width: 100%">
						<tr>
							<td style="text-align: center"><label>메뉴목록</label></td>
						</tr>
						<tr>
							<td><select id=selMenu size=20 style="width: 100%; font-size: 20px;">
								</select></td>
						</tr>
						<tr>
							<td>메뉴명&nbsp;<input type=text id=name readonly></td>
						</tr>
						<tr>
							<td>수량&nbsp;<input type=number id=qty min="1">잔
							</td>
						</tr>
						<tr>
							<td>가격&nbsp;<input type=number id=price readonly>원
							</td>
						</tr>
						<tr>
							<td style="text-align: center;"><button id=btnOrder>주문</button>&nbsp;
								<button id=btnReset>취소</button>
							</td>
						</tr>
					</table>
				</td>
				<td style="width: 33%; border: 1px solid black;; vertical-align: top;">
					<table style="width: 100%">
						<tr>
							<td style="text-align: center"><label>주문내역</label></td>
						</tr>
						<tr>
							<td><select id=selOrder size=20 style="width: 100%;"></select></td>
						</tr>
						<tr>
							<td>모바일:<input type=text id=mobile></td>
						</tr>
						<tr>
							<td>총액:<input type=number id=total value=0 readonly>원
							</td>
						</tr>
						<tr>
							<td style="text-align: center;"><button id=btnDone>주문완료</button>
								<button id=btnClear>취소</button>
							</td>
						</tr>
					</table>
				</td>
				<td style="width: 33%; border: 1px solid black; vertical-align: top;">
					<table style="width: 100%;">
						<tr>
							<td style="text-align: center"><label>실적현황</label></td>
						</tr>
						<tr>
							<td><select id=selSales size=20 style='width: 100%'></select></td>
						</tr>
						<tr>
							<td style="text-align: center;">매출합계:<label id=lblSales>0</label>원
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<a href="#" id="btnMenu">메뉴관리</a>
		<div id="dvMenu" style="display: none;">
			<table>
				<tr>
					<td><select id="selMenu1" size="20" style="width: 300px;"></select>
					</td>
					<td>
						<!-- <input type="hidden" id="optype" value="add" readonly> -->
						<table>
							<tr>
								<td>메뉴명&nbsp;<input type="text" id="_name"></td>
							</tr>
							<tr>
								<td>가격&nbsp;<input type="number" id="_price" step="100"></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td><input type=button id="btnAdd" value="등록"></input>
									<button id="btnDelete">삭제</button>
								</td>
								<td><button id="btnRemove">지우기</button>
									<button id="btnApply">적용</button>
								</td>
								<td><button id="btnCancel">취소</button></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</body>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<script>
		let netprice = 0;
		let arMenu2 = new Array();
		$(document).ready(loadData()).on(
			'click',
			'#btnAdd',
			function () {
				if ($('#_name').val() == '' || $('#_price').val() == '')
					return false;
				if ($('#btnAdd').val() == 'Update') {
					UpdateMenuList($('#_name').val(), $('#_price').val(), $(
						'#selMenu1 option:selected').val())
					console.log('updateMenuList 발동'
						+ $('#selMenu1 option:selected').val())
				} else {
					arMenu2[$('#_name').val()] = $('#_price').val();
					addMenuList($('#_name').val(), $('#_price').val());
				}

				$('#btnRemove').trigger('click');
				return false;
			}).on('click', '#selMenu1 option:selected', function () {
				let arr = $(this).text().split(', ');
				console.log($(this).val())
				$('#_name').prop('readonly', true)
				$('#btnAdd').val('Update')
				$('#_name').val(arr[0]);
				$('#_price').val(arr[1]);
				// $('#optype').val('update');
			}).on('click', '#btnCancel', function () {
				let keys = Object.keys(arMenu2)
				for (let i = 0; i < keys.length; i++) {
					console.log(keys[i])
					delete arMenu2[keys[i]]
				}
				console.log($('#selMenu option').length)
				console.log($('#selMenu option').children().text())
				// for (let i = 0; i < $('#selMenu option').length; i++) {

				//     console.log($('#selMenu').children()[i].text());
				//     // console.log($('#selMenu option');
				//     // arMenu[$('#selMenu').children()[i].val().split(', ')[0]] = $('#selMenu option')[i].val().split(', ')[1];
				//     console.log(arMenu);
				// }
				console.log(arMenu2);
				// $('#selMenu').each(function () {
				//     arMenu[$(this).text().split(', ')[0]] = $(this).text().split(', ')[1];
				// })
				$('#dvMenu').dialog('close');
			}).on('click', '#btnDelete', function () {
				if ($('#selMenu1 option:selected') == null)
					return false;
				console.log($('#selMenu1 option:selected'))
				delete arMenu2[$('#selMenu1 option:selected').text().split(', ')[0]];
				deleteMenu($('#selMenu1 option:selected').val());
				$('#btnRemove').trigger('click');
				return false;
			}).on('click', '#btnRemove', function () {
				$('#_name, #_price').val('');
				$('#_name').prop('readonly', false)
				$('#btnAdd').val('등록')
				// $('#optype').val('add');
				return false;
			}).on('click', '#selMenu option', function () {
				let str = $(this).text();
				console.log(str);
				ar = str.split(',');
				$('#name').val(ar[0]);
				netprice = parseInt(ar[1]);
				$('#price').val(netprice);
				$('#qty').val(1);
				return false;
			}).on('change', '#qty', function () {
				let qty = parseInt($(this).val());
				$('#price').val(qty * netprice);
				return false;
			}).on('click', '#btnReset', function () {
				$('#name,#qty,#price').val('');
				return false;
			}).on('click', '#btnClear', function () {
				$('#selOrder').empty();
				$('#mobile').val('');
				$('#total').val(0);
				return false;
			}).on(
				'click',
				'#btnOrder',
				function () {
					let str = $('#name').val() + ', ' + $('#qty').val() + ', '
						+ $('#price').val();
					str = '<option value=' + $('#selMenu option:selected').val()
						+ '>' + str + '</option>'
					console.log(str);
					$('#selOrder').append(str);
					let total = parseInt($('#total').val())
						+ parseInt($('#price').val());
					$('#total').val(total);

					$('#btnReset').trigger('click');
					return false;
				}).on('click', '#btnClear', function () {
					$('#selOrder').empty();
					$('#mobile').val('');
					$('#total').val(0);
					return false;
				}).on(
					'click',
					'#btnDone',
					function () {
						if ($('#mobile').val() == '') {
							$('#mobile').val('--------');
						}
						let dt = new Date();
						let today = dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-'
							+ dt.getDate() + ' ' + dt.getHours() + ':'
							+ dt.getMinutes();
						let totalprice = parseInt($('#lblSales').text());
						for (let i = 0; i < $('#selOrder option').length; i++) {
							console.log(i)
							console.log($('#selOrder option:eq('+i+')').text())
							let a = $('#selOrder option:eq('+i+')').text().split(', ')
							let str = '<option>' + $('#mobile').val() + ' ' + a[0]
								+ ' ' + a[1] + ' ' + a[2] + ' ' + today;
							totalprice += parseInt(a[2])
							$('#selSales').append(str);
							a[3] = $('#mobile').val()
							a[0] = $(`#selOrder option:eq(${i})`).val();
							a[4] = today;
							addIncome(a);
						}
						$('#lblSales').text(totalprice)
						// let str = '<option>' + today + ' ' + $('#mobile').val() + ', '
						// + $('#total').val() + '</option>';

						// $('#lblSales').text(sum);
						$('#btnClear').trigger('click');
						return false;
					}).on(
						'click',
						'#btnMenu',
						function () {
							$.ajax({
								type: 'get',
								dataType: 'json',
								url: 'menuList',
								success: function (data) {
									for (let i = 0; i < data.length; i++) {
										let jo = data[i]
										let str = '<option value=' + jo['seqNo'] + '>'
											+ jo['name'] + ', ' + jo['price']
											+ '</option>';
										console.log(str)
										$('#selMenu1').append(str);
									}
									$('#dvMenu').dialog('open');
								},
								error: function () {
								},
								complete: function () {
								}
							})
						}).on('click', '#btnApply', function () {
							$('#dvMenu').dialog('close');
						})

		function loadData() {
			$('#selMenu').empty();
			getIncomeList();
			$.ajax({
				type: 'get',
				dataType: 'json',
				url: 'menuList',
				success: function (data) {
					console.log(data[0])
					for (let i = 0; i < data.length; i++) {
						let jo = data[i]
						let str = '<option value=' + jo['seqNo'] + '>' + jo['name']
							+ ', ' + jo['price'] + '</option>';
						console.log(str)
						$('#selMenu').append(str);
					}
				},
				error: function () {
				},
				complete: function () {
				}
			})
			$('#dvMenu').dialog({
				autoOpen: false,
				width: 650,
				height: 550,
				open: function () {
					UpdateDialog();
				},
				close: function () {
					loadData()
				}
			})
		}

		function getIncomeList() {
			$.ajax({
				type: 'get',
				url: 'listIncome',
				dataType: 'json',
				success: function (data) {
					for (let i = 0; i < data.length; i++) {
						let jo = data[i]
						let str = '<option value=' + jo['seqNo'] + '>' + jo['mobile']
							+ ' ' + jo['name'] + ' ' + jo['qty'] + ' '
							+ jo['price'] + ' ' + jo['date'] + '</option>';
						$('#selSales').append(str);
						getSumIncome()
					}
				}
			})
		}

		function getSumIncome() {
			$.ajax({
				type: 'get',
				url: 'getSum',
				dataType: 'text',
				success: function (data) {
					$('#lblSales').text(data)
				}
			})
		}

		function addIncome(str) {
			console.log(str);
			$.ajax({
				type: 'get',
				url: 'addIncome',
				data: {
					'mobile': str[3],
					'seqNo': str[0],
					'menuCount': str[1],
					'totalPrice': str[2],
					'todayDate': str[4]
				},
				success: function () {
				},
				error: function () {
				},
				complete: function () {
				}
			})
		}

		function UpdateMenuList(menuName, menuPrice, seqNo) {
			$.ajax({
				type: 'get',
				url: 'updateMenu',
				data: {
					'menuName': menuName,
					'menuPrice': menuPrice,
					'seqNo': seqNo
				},
				success: function () {
					UpdateDialog()
				},
				error: function () {
				},
				complete: function () {
				}
			})
		}

		function addMenuList(str1, str2) {
			$.ajax({
				type: 'get',
				url: 'addList',
				data: {
					"menuName": str1,
					"menuPrice": str2
				},
				dataType: 'text',
				success: function () {
					UpdateDialog();
				},
				error: function () {
				},
				complete: function () {
				}
			})
		}

		function deleteMenu(seqNo) {
			$.ajax({
				type: 'get',
				url: 'deleteMenu',
				data: {
					'seqNo': seqNo
				},
				success: function () {
					UpdateDialog();
				},
				error: function () {
				},
				complete: function () {
				}
			})
		}

		function getMenuList() {
			$.ajax({
				type: 'get',
				url: 'menuList',
				data: '',
				dataType: 'text',
				success: function (data) {
					let arrTemp = data.split(',');
					for (let i = 0; i < arrTemp.length; i++) {
						let a = arrTemp[i].split(':')
					}
				},
				error: function () {
				},
				complete: function () {
				}
			})
		}

		function UpdateMenu() {
			$('#selMenu').empty();
			$('#selSales').empty();
			$
				.ajax({
					type: 'get',
					url: 'menuList',
					data: '',
					dataType: 'text',
					success: function (data) {
						let arrTemp = data.split(',');
						for (let i = 0; i < arrTemp.length; i++) {
							let a = arrTemp[i].split(':')
							$('#selMenu')
								.append(
									`<option value="${a[2]}">${a[0]}, ${a[1]}</option>`);
						}
						console.log(arMenu2)
					},
					error: function () {
						alert('ready 실패')
					},
					complete: function () {
					}
				})
			$
				.ajax({
					type: 'get',
					url: 'listIncome',
					data: '',
					dataType: 'text',
					success: function (data) {
						let arrTemp = data.split(',');
						let sum1 = 0;
						if (arrTemp[0] != '')
							for (let i = 0; i < arrTemp.length; i++) {
								let a = arrTemp[i].split(':');
								$('#selSales')
									.append(
										`<option value=>${a[0]} ${a[1]} ${a[2]} ${a[3]} ${a[4]}:${a[5]}`)
								sum1 += parseInt(a[3])
							}
						let sum = parseInt($('#lblSales').text()) + sum1;
						$('#lblSales').text(sum);
					},
					error: function () {
					},
					complete: function () {
					}
				})

		}
		function UpdateDialog() {
			$('#selMenu1').empty();
			$('#btnMenu').trigger('click');
			// $('#optype').val('add');
		}

		function getToday() {
			let dt = new Data();
			let today = dt.getFullYear() + '-';
			today += dat.getMonth() < 9 ? '0' + (dt.getMonth() + 1)
				: dt.getMonth() + 1;
			today += '-'
				+ (dat.getDate() < 10 ? '0' + dt.getMonth() : dt.getMonth());
			today += '-'
				+ (dat.getHours() < 9 ? '0' + dt.getHours() : dt.getMonth());
			today += '-'
				+ (dat.getMinutes() < 9 ? '0' + dt.getMinutes() : dt
					.getMinutes());
			return today;
		}
	</script>