package com.jjt.common.entity.message.req;

public final class KfEvent extends BaseEvent {

    private String kfAccount;
    private String closeType;
    public KfEvent(String kfAccount,String closeType) {
    	this.kfAccount = kfAccount;
    	this.closeType = closeType;
	}
    
	@Override
	public String toString() {
		return "KfEvent [kfAccount=" + kfAccount + ", closeType=" + closeType + "]";
	}
	public String getKfAccount() {
		return kfAccount;
	}
	public void setKfAccount(String kfAccount) {
		this.kfAccount = kfAccount;
	}
	public String getCloseType() {
		return closeType;
	}
	public void setCloseType(String closeType) {
		this.closeType = closeType;
	}



}
