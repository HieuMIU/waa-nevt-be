package nevt.dto.account;

public class JwtAuthenticationResponse {
  String token;

  String firstName;

  String lastName;

  String email;

  public JwtAuthenticationResponse() {
  }

  public JwtAuthenticationResponse(String token, String firstName, String lastName, String email) {
    this.token = token;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public String getToken() {
    return token;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }
}
