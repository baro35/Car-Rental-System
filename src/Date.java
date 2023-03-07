
public class Date {
	
	private String day;
	private String month;
	private String year;
	private boolean flag;
	
	//CONSTRUCTOR
		public Date(String day, String month, String year) {
			if((month.contains("1") || month.contains("3") || month.contains("5") || month.contains("7") || month.contains("8") ||
					month.contains("10") || month.contains("12")) && (Integer.parseInt(day.replace(" ", "")) > 0 && Integer.parseInt(day.replace(" ", "")) <= 31)) {
				this.day = day;
				this.month = month;
				this.year = year;
				flag = true;
			}
			else if((month.contains("2") || month.contains("4") || month.contains("6") || month.contains("9") || month.contains("11"))
					&& (Integer.parseInt(day.replace(" ", "")) > 0 && Integer.parseInt(day.replace(" ", "")) <= 30)) {
				this.day = day;
				this.month = month;
				this.year = year;
				flag = true;
			}
			else flag = false;
		}
	
	public String listDate() {
		return day + "." + month + "." + year;
	}

	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
