public class MyString implements MyComparable
{
	private char[] letters;
	final int NOT_FOUND = -1;

	public MyString( String other )
	{
		letters = other.toCharArray();
	}

	public MyString( MyString other )
	{
		this( other.toString() );
	}

	public String toString()
	{
		return new String(letters);
	}

	public int length()
	{
		return letters.length;
	}

	public char charAt(int index)
	{
		return letters[index];
	}

///////////////// Y O U    M U S T    W R I T E    T H E S E    T W O    M E T H O D S //////////////

	//RETURNS 0 if strings are lexically identical in every way, +1 if this string greater, else -1
	public int myCompareTo(MyString other)
	{
		MyString str1 = this;
        MyString str2 = other;

        if(str1 == null && str2 == null){
            return 0;
        }
        if(str1 == null && str2 != null){
            return 1;
        }else if(str1 != null && str2 == null){
            return -1;

        }
        
        
        for(int i = 0; i<str1.length() && i<str2.length(); i++){
            if(str1.charAt(i) ==  )

        }

	}

	//RETURNS 0 iff strings are lexically identical
	public boolean equals(MyString other)
	{
		return this==other; //just to make it compile
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////

} // END MYSTRING CLASS
