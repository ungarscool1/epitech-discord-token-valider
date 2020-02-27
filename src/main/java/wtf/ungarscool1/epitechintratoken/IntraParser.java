package wtf.ungarscool1.epitechintratoken;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import org.javacord.api.entity.message.embed.EmbedBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class IntraParser {
	
	private String page = "";
	private String url;
	JsonObject obj;
	private String error;
	
	public IntraParser(String autolink) {
		this.url = "https://intra.epitech.eu/" + autolink;
		setDefaultCookie();
		call();
	}
	
	private void setDefaultCookie() {
		CookieManager cm = new CookieManager();
		CookieHandler.setDefault(cm);
	}
	
	private void call() {
		try {
			URL uri = new URL(this.url + "/?format=json");
			HttpsURLConnection cl = (HttpsURLConnection) uri.openConnection();
			cl.setRequestProperty("Accept", "application/json");
			cl.setRequestProperty("Content-Type", "application/json");
			cl.setDoOutput(true);
			cl.setDoInput(true);
			cl.connect();
			BufferedReader rd = new BufferedReader(new InputStreamReader(cl.getInputStream()));
			String line;
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			while ((line = rd.readLine()) != null) {
				page += line + "\n";
			}
			obj = gson.fromJson(this.page, JsonObject.class);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public EmbedBuilder validateToken(String imgPath) {
		String token = "";
		Matcher match = null;
		JsonObject activity = selectProperElem();
		EmbedBuilder embed = new EmbedBuilder().setTitle("Validation du token").setFooter("Epitech's API Connector v.1");

		if (activity == null) {
			embed.addField(":warning:", "Aucune activité n'est à valider !");
			embed.setColor(Color.red);
		} else {
			try {
				token = GoogleVision.detectText(imgPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			match = Pattern.compile("(\\d+){8}").matcher(token);
			while (match.find()) {
				token = match.group();
			}
			embed.addField("N° token:", token);
			embed.addField("Validation sur l'activité", activity.get("title").getAsString());
			if (validateHttp(token, activity.get("token_link").getAsString()) == 1) {
				embed.setColor(Color.GREEN);
			} else {
				embed.addField(":warning:", error);
				embed.setColor(Color.RED);
			}
		}
		return embed;
	}
	
	private int validateHttp(String token, String validationLink) {
		try{
            URL uri = new URL(this.url + validationLink +"/?format=json");
            HttpsURLConnection connection = (HttpsURLConnection) uri.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write("token=" + token + "&rate=0&comment=");
            writer.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String res ;
            while ((res = in.readLine()) != null) {
                stringBuffer.append(res);
            }
            in.close();
            if (stringBuffer.toString().equalsIgnoreCase("{}")) {
            	return 1;
            } else {
            	error = "Le token n'est pas valide !";
            	return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            error = "Lien non trouvé / Mauvais response code";
            return 0;
        }
	}
	
	private JsonObject selectProperElem() {
		JsonArray array = obj.get("board").getAsJsonObject().get("activites").getAsJsonArray();
		
		for (int i = 0; i < array.size(); i++) {
			JsonObject object = array.get(i).getAsJsonObject();
			if (!object.get("token").isJsonNull())
				return object;
		}
		return null;
	}
	
	public String getPageasString() {
		return this.page;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
