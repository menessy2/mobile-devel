package com.hasebly;

import IDTech.MSR.XMLManager.StructConfigParameters;
import android.content.Context;
import android.content.SharedPreferences;

import com.idtechproducts.unipay.UniPayReader;
import com.idtechproducts.unipay.UniPayReaderMsg;

public class UniPayInterface implements UniPayReaderMsg{

	UniPayReader reader;
	
	protected UniPayInterface(Context userContext)
	{
		reader = new UniPayReader(this, userContext);
		reader.registerListen();
	}
	
	
	protected void destroyReader()
	{
		reader.release();
		reader.unregisterListen();
	}
	
	
	public void bindWithSettings(SharedPreferences sharedPreferences) {
		StructConfigParameters profile = new StructConfigParameters();
        profile.setDirectionOutputWave((short)sharedPreferences.getInt("directionOutputWave", 1));
        profile.setFrequenceInput(sharedPreferences.getInt("frequenceInput", 1));
        profile.setFrequenceOutput(sharedPreferences.getInt("frequenceOutput", 1));
        profile.setRecordBufferSize(sharedPreferences.getInt("recordBufferSize", 1));
        profile.setRecordReadBufferSize(sharedPreferences.getInt("recordReadBufferSize", 1));
        profile.setWaveDirection(sharedPreferences.getInt("waveDirection", 1));
        profile.sethighThreshold((short) sharedPreferences.getInt("highThreshold", 1));
        profile.setlowThreshold((short) sharedPreferences.getInt("lowThreshold", 1));
        profile.setMin((short) sharedPreferences.getInt("min", 1));
        profile.setMax((short) sharedPreferences.getInt("max", 1));
        profile.setBaudRate(sharedPreferences.getInt("baudRate", 1));
        profile.setPreAmbleFactor((short) sharedPreferences.getInt("preAmbleFactor", 1));
        profile.setShuttleChannel((byte) sharedPreferences.getInt("shuttleChannel", 1));
        profile.setForceHeadsetPlug((short) sharedPreferences.getInt("useVoiceRecognition", 1));
        profile.setUseVoiceRecognition((short) sharedPreferences.getInt("volumeLevelAdjust", 1));
        profile.setVolumeLevelAdjust((short) sharedPreferences.getInt("frequenceInput", 1));
	}

	
	
	
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
