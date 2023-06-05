package utils;

//import engine.chat.ChatManager;
//import engine.users.UserManager;

import jakarta.servlet.ServletContext;
import logic.Ally;
import logic.BattleField;
import logic.Engine;
import logic.EngineFirst;
import users.AlliesManager;
import users.BattleFieldManager;
import users.UserUBoatManager;

import java.util.ArrayList;

//import static chat.constants.ConstantsWeb.INT_PARAMETER_ERROR;

public class ServletUtils {

	private static final String USER_MANAGER_ATTRIBUTE_NAME_U_BOAT = "userUBoatManager";
	private static final String All_BATTLE_FIELDS_ATTRIBUTE_NAME="allBattleFields";
	private static final String ENGINE_ATTRIBUTE_NAME="engine";
	private static final String CHAT_MANAGER_ATTRIBUTE_NAME = "chatManager";
	private static final String ALL_ALLIES_NAMES="allAlliesManager";

	/*
	Note how the synchronization is done only on the question and\or creation of the relevant managers and once they exists -
	the actual fetch of them is remained un-synchronized for performance POV
	 */
	private static final Object userManagerLockForUBoat = new Object();
	private static final Object chatManagerLock = new Object();

	public static UserUBoatManager getUserUBoatManager(ServletContext servletContext) {

		synchronized (userManagerLockForUBoat) {
			if (servletContext.getAttribute(USER_MANAGER_ATTRIBUTE_NAME_U_BOAT) == null) {
				servletContext.setAttribute(USER_MANAGER_ATTRIBUTE_NAME_U_BOAT, new UserUBoatManager());
			}
		}
		return (UserUBoatManager) servletContext.getAttribute(USER_MANAGER_ATTRIBUTE_NAME_U_BOAT);
	}
	public static BattleFieldManager getAllBattleFieldsManager(ServletContext servletContext){
		synchronized (userManagerLockForUBoat){
			if(servletContext.getAttribute(All_BATTLE_FIELDS_ATTRIBUTE_NAME)==null){
				servletContext.setAttribute(All_BATTLE_FIELDS_ATTRIBUTE_NAME,new BattleFieldManager());
			}
		}
		return (BattleFieldManager) servletContext.getAttribute(All_BATTLE_FIELDS_ATTRIBUTE_NAME);
	}


	public static void setBattleField(String UBoatName,ServletContext servletContext,BattleField battleField){

		synchronized (userManagerLockForUBoat){
				servletContext.setAttribute(UBoatName, battleField);
		}

	}
	public static void eraseBattleField(String UBoatName,ServletContext servletContext){
		synchronized (userManagerLockForUBoat){
			servletContext.removeAttribute(UBoatName);
		}
	}
	public static BattleField getBattleField(String UBoatName,ServletContext servletContext){

		synchronized (userManagerLockForUBoat) {
			return (BattleField) servletContext.getAttribute(UBoatName);
		}
	}
	public static AlliesManager getAllAlliesManager(ServletContext servletContext){
		synchronized (userManagerLockForUBoat){
			if(servletContext.getAttribute(ALL_ALLIES_NAMES)==null){
				servletContext.setAttribute(ALL_ALLIES_NAMES,new AlliesManager());
			}
		}
		return (AlliesManager) servletContext.getAttribute(ALL_ALLIES_NAMES);
	}





	public static Engine getEngine(ServletContext servletContext){
		if(servletContext.getAttribute(ENGINE_ATTRIBUTE_NAME)==null){
			Engine engine=new EngineFirst();
			servletContext.setAttribute(ENGINE_ATTRIBUTE_NAME,engine);
		}
		return (Engine) servletContext.getAttribute(ENGINE_ATTRIBUTE_NAME);
	}
	/*

	public static ChatManager getChatManager(ServletContext servletContext) {
		synchronized (chatManagerLock) {
			if (servletContext.getAttribute(CHAT_MANAGER_ATTRIBUTE_NAME) == null) {
				servletContext.setAttribute(CHAT_MANAGER_ATTRIBUTE_NAME, new ChatManager());
			}
		}
		return (ChatManager) servletContext.getAttribute(CHAT_MANAGER_ATTRIBUTE_NAME);
	}

	public static int getIntParameter(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		if (value != null) {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException numberFormatException) {
			}
		}
		return INT_PARAMETER_ERROR;
	}

 */
}
