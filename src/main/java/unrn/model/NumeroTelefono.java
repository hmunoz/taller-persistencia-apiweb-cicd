package unrn.model;

public class NumeroTelefono {
  static final String ERROR_CODIGO_INVALIDO = "El código de área debe tener 4 dígitos";
  static final String ERROR_NUMERO_LARGO = "El número no debe tener más de 7 caracteres";
  static final String ERROR_NUMERO_CORTO = "El número debe tener al menos 6 caracteres";
  static final String ERROR_NUMERO_INVALIDO = "El número no puede ser null";

  private final String codigoArea;
  private final String numero;

  public NumeroTelefono(String codigoArea, String numero) {
    assertCodigoArea(codigoArea);
    assertNumberInvalido(numero);
    assertNumeroLargo(numero);
    assertNumeroCorto(numero);
    this.codigoArea = codigoArea;
    this.numero = numero;
  }

  private void assertNumberInvalido(String numero) {
    if (numero == null) {
      throw new RuntimeException(ERROR_NUMERO_INVALIDO);
    }
  }

  public String numero() {
    return codigoArea + " " + numero;
  }

  private void assertCodigoArea(String codigoArea) {
    if (codigoArea == null
        || codigoArea.length() != 4
        || !codigoArea.chars().allMatch(Character::isDigit)) {
      throw new RuntimeException(ERROR_CODIGO_INVALIDO);
    }
  }

  private void assertNumeroLargo(String numero) {
    if (numero.length() > 7) {
      throw new RuntimeException(ERROR_NUMERO_LARGO);
    }
  }

  private void assertNumeroCorto(String numero) {
    if (numero.length() < 6) {
      throw new RuntimeException(ERROR_NUMERO_CORTO);
    }
  }
}
