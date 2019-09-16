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
  
  private boolean isValidInput(char current) {
	  if (Character.isDigit(current)) {
		  return true;
	  }
	  
	  switch(current) {
	  case '+':
		  return true;
	  case '-':
		  return true;
	  case '/':
		  return true;
	  case '*':
		  return true;
	  case '<':
		  return true;
	  case '>':
		  return true;
	  default:
		  return false;
	  }
  }
  
  private TokenType getTokenType(char current, char lookAhead) {
	  if (Character.isDigit(current)) {
		  return TokenType.NUM;
	  }
	  switch(current) {
	  case '+':
		  return TokenType.PLUS;
	  case '-':
		  return TokenType.MINUS;
	  case '/':
		  return TokenType.DIV;
	  case '*':
		  return TokenType.MUL;
	  case '<':
		  if (lookAhead == '=') {
			  return TokenType.LTE;
		  }
		  return TokenType.LT;
	  case '>':
		  if (lookAhead == '=') {
			  return TokenType.GTE;
		  }
		  return TokenType.GT;
	  default:
		  return null;
	  }
  }
  
  private String getFullNumber(StringBuilder stream) {
	  String number = "";
	  for (int i = 0; i < stream.length(); i++) {
		  char current = stream.charAt(i);
		  if (!Character.isDigit(current)) {
			  return number;
		  }
		  number += current;
	  }
	  return number;
  }

  public Token extractToken(StringBuilder stream){
    /* TODO #2: Extract the next token in the string, or report an error*/
	char current = '\0', lookAhead = '\0';
	if (stream.length() == 0) {
		throw new IllegalArgumentException("ERROR: Input stream is empty");
	}
	
	current = stream.charAt(0);
	if (Character.isWhitespace(current)) {
		stream.delete(0, 1);
		return extractToken(stream);
	}
	
	if (!isValidInput(current)) {
		throw new IllegalArgumentException("ERROR: Illegal character in input stream");
	}
	
	if (Character.isWhitespace(lookAhead) || stream.length() == 1) {
		TokenType currentType = getTokenType(current, lookAhead);
		stream.delete(0, 1);
		return new Token(currentType, Character.toString(current));
	}
	else {
		lookAhead = stream.charAt(1);
		TokenType currentType = getTokenType(current, lookAhead);
		
		if (currentType == TokenType.NUM) {
			String fullNum = getFullNumber(stream);
			stream.delete(0, fullNum.length());
			return new Token(currentType, fullNum);
		}
		else if (currentType == TokenType.GTE || currentType == TokenType.LTE) {
			stream.delete(0, 2);
			return new Token(currentType, current + "=");
		}
		else {
			stream.delete(0, 1);
			return new Token(currentType, Character.toString(current));
		}
	}
  }

  public String extractTokens(String arg){
    /* TODO #1: Finish this function to iterate over all tokens in the input string.

       Pseudo code:
       String extractTokens(String arg):
         String result= "";
         while(arg is not empty)
            Token nextToken = extractToken(arg)
            result += nextToken.toString()
         return result
    */
    return null;
  }

}
