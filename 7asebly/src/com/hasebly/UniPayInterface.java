package com.hasebly;

import java.util.ArrayList;

import IDTech.MSR.XMLManager.StructConfigParameters;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.idtechproducts.unipay.UniPayReader;
import com.idtechproducts.unipay.UniPayReaderMsg;

public class UniPayInterface implements UniPayReaderMsg{

	protected UniPayReader reader;
	protected byte[] byteAnswer;
	protected boolean completedInstruction = false;
	protected Context userContext;
	CallbackFunctionsInterface actionListner;
	
	protected UniPayInterface(Context userContext)
	{
		reader = new UniPayReader(this, userContext);
		reader.registerListen();
		this.userContext = userContext;
		actionListner = (CallbackFunctionsInterface) userContext;
	}
	
	protected void sendAPDU(ArrayList<Byte> instruction)
	{
		byte[] toSend = new byte[instruction.size()];
		for(int x= 0; x < instruction.size(); x++)
			toSend[x] = instruction.get(x);
		reader.sendCommandExchangeAPDUPlaintext(toSend);			
	}
	
	protected void readMagneticStrip()
	{
		reader.startSwipeCard();
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
	public void onReceiveMsgAutoConfigCompleted(StructConfigParameters settings) {
		SharedPreferences sharedPreferences = userContext.getSharedPreferences("Reader_Setings_7aseblySDK", Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putInt("directionOutputWave",settings.getWaveDirection());
		editor.putInt("frequenceInput",settings.getFrequenceInput());
		editor.putInt("frequenceOutput",settings.getFrequenceOutput());
		editor.putInt("recordBufferSize",settings.getRecordBufferSize());
		editor.putInt("recordReadBufferSize",settings.getRecordReadBufferSize());
		editor.putInt("waveDirection",settings.getWaveDirection());
		editor.putInt("highThreshold",settings.gethighThreshold());
		editor.putInt("lowThreshold",settings.getlowThreshold());
		editor.putInt("min",settings.getMin());
		editor.putInt("max",settings.getMax());
		editor.putInt("baudRate",settings.getBaudRate());
		editor.putInt("preAmbleFactor",settings.getPreAmbleFactor());
		editor.putInt("shuttleChannel",settings.getShuttleChannel());
		editor.putInt("forceHeadsetPlug",settings.getForceHeadsetPlug());
		editor.putInt("useVoiceRecognition",settings.getUseVoiceRecognition());
		editor.putInt("volumeLevelAdjust",settings.getVolumeLevelAdjust());
		editor.commit();
		notify();
	}

	@Override
	public void onReceiveMsgAutoConfigProgress(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveMsgAutoConfigProgress(int arg0, double arg1,
			String arg2) {
		
		
	}

	@Override
	public void onReceiveMsgCardData(byte arg0, byte[] answer) {
		byteAnswer = answer;
		completedInstruction = true;
		notify();
	}

	@Override
	public void onReceiveMsgCommandResult(int type, byte[] answer) {
		switch(type){
		case UniPayReaderMsg.cmd3VThen5V:
			
			break;
		case UniPayReaderMsg.cmdCancelSwipingMSRCard:
			
			break;
		case UniPayReaderMsg.cmdClearBuffer:
			
			break;
		case UniPayReaderMsg.cmdDefaultICCGroupSetting:
			
			break;
		case UniPayReaderMsg.cmdDefaultMSRSetting:
			
			break;
		case UniPayReaderMsg.cmdEnableSwipingMSRCard:
			
			break;
		case UniPayReaderMsg.cmdEncryptOptionSetting:
			
			break;
		case UniPayReaderMsg.cmdExchangeAPDUEncrytion:
			
			break;
		case UniPayReaderMsg.cmdExchangeAPDUPlaintext:
			byteAnswer = answer;
			notify();
			break;
		case UniPayReaderMsg.cmdGetAttachedReaderType:
			
			break;
		case UniPayReaderMsg.cmdGetAudioJackBaudRate_Level:
			
			break;
		case UniPayReaderMsg.cmdGetICCEncryptionModeOfDUKPT:
			
			break;
		case UniPayReaderMsg.cmdGetICCKeyTypeOfDUKPT:
			
			break;
		case UniPayReaderMsg.cmdGetICCStatus:
			
			break;
		case UniPayReaderMsg.cmdGetModelNumber:
			
			break;
		case UniPayReaderMsg.cmdGetOtherCommonSetting:
			
			break;
		case UniPayReaderMsg.cmdGetSendOption:
			
			break;
		case UniPayReaderMsg.cmdGetSerialNumber:
			
			break;
		case UniPayReaderMsg.cmdGetVersion:
			
			break;
		case UniPayReaderMsg.cmdHashOptionSetting:
			
			break;
		case UniPayReaderMsg.cmdMaskOptionSetting:
			
			break;
		case UniPayReaderMsg.cmdPowerOffICC:
			
			break;
		case UniPayReaderMsg.cmdPowerOnICC:
			
			break;
		case UniPayReaderMsg.cmdReset:
			
			break;
		case UniPayReaderMsg.cmdReviewAudioJackSetting:
			
			break;
		case UniPayReaderMsg.cmdReviewICCGroupSetting:
			
			break;
		case UniPayReaderMsg.cmdReviewICCPowerSetting:
			
			break;
		case UniPayReaderMsg.cmdReviewMSRSetting:
			
			break;
		case UniPayReaderMsg.cmdSet5V:
			
			break;
		case UniPayReaderMsg.cmdSetICCEncryptionModeOfDUKPT:
			
			break;
		case UniPayReaderMsg.cmdSetICCKeyTypeOfDUKPT:
			
			break;
		case UniPayReaderMsg.cmdSetOtherCommonSetting:
			
			break;
		case UniPayReaderMsg.cmdSetSendOption:
			
			break;
		case UniPayReaderMsg.cmdSwipeCard:
			
			break;
		case UniPayReaderMsg.cmdTestCommand:
			
			break;
		case UniPayReaderMsg.cmdTestSwipeCard:
			
			break;
		case UniPayReaderMsg.typeToOverwriteXML:
			
			break;
		case UniPayReaderMsg.typeToPowerupUniPay:
			
			break;
		case UniPayReaderMsg.typeToReportToIdtech:
			
			break;
		case UniPayReaderMsg.typeToTryAutoConfig:
			
			break;
		case UniPayReaderMsg.typeToUpdateXML:
			
			break;
		}
		
	}			
	

	@Override
	public void onReceiveMsgConnected() {
		actionListner.readerPluggedIn();
	}

	@Override
	public void onReceiveMsgDisconnected() {
		actionListner.readerunplugged();
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
