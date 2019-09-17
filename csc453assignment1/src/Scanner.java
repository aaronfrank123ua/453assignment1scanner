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
      return "|" + this.tokenType + ": " + this.tokenVal + "|";
    }
  }
  
  //Returns a boolean stating whether or not the character is a valid input character for the scanner
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
  
  //Returns the TokenType assosciated with a given character
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
  
  //
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

  //Extracts the first token in a given StingBuilder
  //Returns a Token object representing this token
  public Token extractToken(StringBuilder stream){
	char current = '\0', lookAhead = '\0';
	//If the stream is empty, then this is an improper call to the method
	if (stream.length() == 0) {
		System.out.println("ERROR: Input stream is empty");
		throw new IllegalArgumentException("ERROR: Input stream is empty");
	}
	
	current = stream.charAt(0);
	//If the current character is a whitespace, it is not a token, so keep searching
	if (Character.isWhitespace(current)) {
		stream.delete(0, 1);
		return extractToken(stream);
	}
	
	//If the input is invalid, throw an exception
	if (!isValidInput(current)) {
		System.out.println("ERROR: Illegal character in input stream");
		throw new IllegalArgumentException("ERROR: Illegal character in input stream");
	}
	
	//If the next character is a whitespace or at the end of the String, we know the current
	//token ends here
	if (Character.isWhitespace(lookAhead) || stream.length() == 1) {
		TokenType currentType = getTokenType(current, lookAhead);
		stream.delete(0, 1);
		return new Token(currentType, Character.toString(current));
	}
	else {
		lookAhead = stream.charAt(1);
		TokenType currentType = getTokenType(current, lookAhead);
		
		//If the current character is a digit, we need to extract the whole number
		if (currentType == TokenType.NUM) {
			String fullNum = getFullNumber(stream);
			stream.delete(0, fullNum.length());
			return new Token(currentType, fullNum);
		}
		//the remaining operators are of length 1, but GTE and LTE are of length 2
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

  //Extracts Tokens from a given string, and returns them in their string representation
  public String extractTokens(String arg){
	String result = "";
	StringBuilder inputStream = new StringBuilder(arg);
	while (inputStream.length() > 0) {
		Token nextToken = null;
		try {
			nextToken = extractToken(inputStream);
		} 
		//If an error occurs, just return an empty string
		catch (IllegalArgumentException e) {
			return "";
		}
		result += nextToken.toString();
	}
    return result;
  }

}
