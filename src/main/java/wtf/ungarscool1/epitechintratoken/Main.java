package wtf.ungarscool1.epitechintratoken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	public static Configuration config;
	
	public static void main(String[] args) {
		DiscordApi api;
		BufferedReader rd;
		Gson gson;

		try {
			rd = new BufferedReader(new FileReader(new File("./config.json")));
			gson = new GsonBuilder().setPrettyPrinting().create();
			config = gson.fromJson(rd, Configuration.class);
		} catch (FileNotFoundException e) {
			System.err.println("Le fichier n'a pas été trouvé ou vous n'avez pas les droits.\n");
			System.exit(84);
		}
		config.checkConfiguration();
		api = new DiscordApiBuilder().setToken(config.botToken).login().join();
		api.addMessageCreateListener(new MessageListener());
	}
}
