import java.io.Serializable;

public class menuDTO implements Serializable{
	private String menuName;
	private int menuPrice;
	private int seqNo;

	public menuDTO(String menuName, int menuPrice, int seqNo) {
		super();
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.seqNo = seqNo;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public menuDTO(String menuName, int menuPrice) {
		super();
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}

	public menuDTO() {
		super();
	}
}
