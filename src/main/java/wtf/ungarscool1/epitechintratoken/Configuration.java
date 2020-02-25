package wtf.ungarscool1.epitechintratoken;

public class Configuration {

	public String botToken;
	public String epiToken;
	public String cookie_user;
	
	public void checkConfiguration() {
		if (botToken == "" || botToken == null || epiToken == ""
				|| epiToken == null || cookie_user == "" || cookie_user == null) {
			System.err.println("Votre configuration n'est pas valide.");
			System.err.println("Vérifier les champs suivant dans le config.json:");
			System.err.println("\t- botToken\n\t- epiToken\n\t- cookie_user");
			System.exit(84);
		}
	}
}