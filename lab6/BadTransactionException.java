public class BadTransactionException extends Exception {

  public int transAmount;  

  public BadTransactionException(int badTransaction) {
    super("Invalid transaction number: " + badTransaction);

    transAmount = badTransaction;
  }
}