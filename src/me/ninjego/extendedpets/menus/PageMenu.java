package me.ninjego.extendedpets.menus;

public abstract class PageMenu extends Menu {

	protected int page = 0;
	
	protected int maxItemsPerPage;
	
	protected int index = 0;
	
	public PageMenu(PlayerMenu playerMenu, int maxItemsPerPage) {
		super(playerMenu);
		this.maxItemsPerPage = maxItemsPerPage;
	}
	
	public int getMaxItemsPerPage() {
		return maxItemsPerPage;
	}
	
	public void setMaxItemsPerPage(int maxItemsPerPage) {
		this.maxItemsPerPage = maxItemsPerPage;
	}

}
