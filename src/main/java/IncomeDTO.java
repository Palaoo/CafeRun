
public class IncomeDTO {
	private String mobile;
	private int seqNo;
	private int qty;
	private String price;
	private String income_date;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IncomeDTO(String mobile, int seqNo, int qty, String price, String income_date) {
		super();
		this.mobile = mobile;
		this.seqNo = seqNo;
		this.qty = qty;
		this.price = price;
		this.income_date = income_date;
	}

	public IncomeDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getIncome_date() {
		return income_date;
	}

	public void setIncome_date(String income_date) {
		this.income_date = income_date;
	}
}
