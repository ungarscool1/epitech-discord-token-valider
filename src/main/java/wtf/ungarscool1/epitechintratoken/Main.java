package wtf.ungarscool1.epitechintratoken;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

	public static void main(String[] args) {
		DiscordApi api = new DiscordApiBuilder().setToken("NjQ2ODA0NDE3ODQ0MzQ2OTEx.Xkxehw.JGrYbv3RRDj44j0syD1vJYd_R_I").login().join();
		api.addMessageCreateListener(new MessageListener());
	}
}
