package wtf.ungarscool1.epitechintratoken;

public class Configuration {

	public String botToken;
	public String epiToken;
	
	public void checkConfiguration() {
		if (botToken == "" || botToken == null || epiToken == ""
				|| epiToken == null) {
			System.err.println("Votre configuration n'est pas valide.");
			System.err.println("Vérifier les champs suivant dans le config.json:");
			System.err.println("\t- botToken\n\t- epiToken");
			System.exit(84);
		}
	}
}