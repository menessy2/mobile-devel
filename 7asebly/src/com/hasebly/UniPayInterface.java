package com.hasebly;

import IDTech.MSR.XMLManager.StructConfigParameters;
import com.idtechproducts.unipay.UniPayReaderMsg;

public class UniPayInterface implements UniPayReaderMsg{

	@Override
	public boolean getUserGrant(int arg0, String arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onReceiveMsgAutoConfigCompleted(StructConfigParameters arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveMsgAutoConfigProgress(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveMsgAutoConfigProgress(int arg0, double arg1,
			String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveMsgCardData(byte arg0, byte[] arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveMsgCommandResult(int arg0, byte[] arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveMsgConnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveMsgDisconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveMsgFailureInfo(int arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveMsgProcessingCardData() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onReceiveMsgTimeout(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveMsgToConnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveMsgToSwipeCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Deprecated
	public void onReceiveMsgSDCardDFailed(String arg0) {
		// TODO Auto-generated method stub
		
	}

}
