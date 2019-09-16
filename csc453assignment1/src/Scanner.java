public class Scanner{
  enum TokenType{
      NUM, PLUS, MINUS, MUL, DIV, LT, LTE, GT, GTE;
  }

  class Token{
    TokenType tokenType;
    String tokenVal;
    public Token(TokenType tokenType, String tokenVal){
      this.tokenType = tokenType;
      this.tokenVal = tokenVal;
    }
    public String toString(){
      return this.tokenType + ": " + this.tokenVal + " ";
    }
  }

  public Token extractToken(StringBuilder stream){
    /* TODO #2: Extract the next token in the string, or report an error*/
    return null;
  }

  public String extractTokens(String arg){
    /* TODO #1: Finish this function to iterate over all tokens in the input string.

       Pseudo code:
       String extractTokens(String arg):
         String result= “”;
         while(arg is not empty)
            Token nextToken = extractToken(arg)
            result += nextToken.toString()
         return result
    */
    return null;
  }

}
