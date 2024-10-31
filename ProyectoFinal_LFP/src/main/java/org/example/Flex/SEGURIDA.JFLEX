package org.example.Flex;
import org.example.backend.Enums.TipoToken;
import org.example.backend.Tokens.Token;

%%

%public
%class SQLLexer
%char
%unicode
%line
%column
%ignorecase
%standalone
%type Token
NUMERO = "-"?[0-9]+
DECIMAL = "-"?[0-9]+"."[0-9]+
FECHA = [0-9]{4}-[0-9]{2}-[0-9]{2} //fechas en formato yyyy-mm-dd
CADENA = "'"( [^'] | "''" )*"'" //cadenas de texto
MAYUSCULA = [A-Z]
MINUSCULA = [a-z]
PALABRA = [a-zA-Z]+
ESPACIO_TEXTO = [" "\n\t\r\f]+
    SPACE = " "
    NEWLINE = \n|\r|\r\n

//inicial los valores d filas y columnas
// %init{
//     yycolumn = 0;
//     yyline = 1;
// %init}

// indicar el fin de archivo
%eofval{
    return new Token(TipoToken.EOF, yyline, yycolumn, "Fin de archivo");
%eofval}

%line
%char
%%
<YYINITIAL> "CREATE" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "DATABASE" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "TABLE" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "KEY" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "NULL" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "PRIMARY" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "UNIQUE" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "FOREIGN" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "REFERENCES" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "ALTER" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "ADD" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "COLUMN" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "TYPE" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "DROP" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "CONSTRAINT" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "IF" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "EXIST" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "CASCADE" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "ON" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "DELETE" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "SET" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "UPDATE" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "INSERT" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "INTO" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "VALUES" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "SELECT" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "FROM" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "WHERE" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "AS" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "GROUP" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "ORDER" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "BY" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "ASC" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "DESC" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "LIMIT" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }
<YYINITIAL> "JOIN" { return new Token(TipoToken.CREATE, yyline, yychar, yytext()); }

{SPACE} {
}
// <YYINITIAL> "INTEGER" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.TIPO_DE_DATO, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "BIGINT" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.TIPO_DE_DATO, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "VARCHAR" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.TIPO_DE_DATO, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "DECIMAL" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.TIPO_DE_DATO, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "DATE" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.TIPO_DE_DATO, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "TEXT" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.TIPO_DE_DATO, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "BOOLEAN" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.TIPO_DE_DATO, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "SERIAL" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.TIPO_DE_DATO, yyline, inicioColumna, yytext()); }

// <YYINITIAL> {NUMERO}+ { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.ENTERO, yyline, inicioColumna, yytext()); }

// <YYINITIAL> {DECIMAL} { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.DECIMAL, yyline, inicioColumna, yytext()); }

// <YYINITIAL> {FECHA} { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.FECHA, yyline, inicioColumna, yytext()); }

// <YYINITIAL> {CADENA} { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.CADENA, yyline, inicioColumna, yytext()); }

// <YYINITIAL> "TRUE" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.BOOLEANO, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "FALSE" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.BOOLEANO, yyline, inicioColumna, yytext()); }

// <YYINITIAL> "SUM" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.FUNCION_AGREGACION, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "AVG" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.FUNCION_AGREGACION, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "COUNT" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.FUNCION_AGREGACION, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "MAX" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.FUNCION_AGREGACION, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "MIN" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.FUNCION_AGREGACION, yyline, inicioColumna, yytext()); }

// <YYINITIAL> "(" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.SIGNOS, yyline, inicioColumna, yytext()); }
// <YYINITIAL> ")" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.SIGNOS, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "," { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.SIGNOS, yyline, inicioColumna, yytext()); }
// <YYINITIAL> ";" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.SIGNOS, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "." { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.SIGNOS, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "=" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.SIGNOS, yyline, inicioColumna, yytext()); }

// <YYINITIAL> "+" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.ARITMETICO, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "-" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.ARITMETICO, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "*" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.ARITMETICO, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "/" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.ARITMETICO, yyline, inicioColumna, yytext()); }

// <YYINITIAL> "<" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.RELACIONAL, yyline, inicioColumna, yytext()); }
// <YYINITIAL> ">" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.RELACIONAL, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "<=" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.RELACIONAL, yyline, inicioColumna, yytext()); }
// <YYINITIAL> ">=" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.RELACIONAL, yyline, inicioColumna, yytext()); }

// <YYINITIAL> "AND" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.LOGICO, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "OR" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.LOGICO, yyline, inicioColumna, yytext()); }
// <YYINITIAL> "NOT" { int inicioColumna = yycolumn; yycolumn += yylength(); return new Token(TipoToken.LOGICO, yyline, inicioColumna, yytext()); }

// <YYINITIAL> "--".* { 
//     int inicioColumna = yycolumn;
//     for (int i = 0; i < yylength(); i++) {
//         if (yytext().charAt(i) == '\n') {
//             yyline++;  
//             yycolumn = 0; // Reseteamos columna en nueva línea
//         } else {
//             yycolumn++; // Solo incrementamos columna si no es un salto de línea
//         }
//     }
//     return new Token(TipoToken.COMENTARIO, yyline, inicioColumna, yytext());
// }

// <YYINITIAL> {ESPACIO_TEXTO} { 
//     int inicioColumna = yycolumn;
//     for (int i = 0; i < yylength(); i++) {
//         if (yytext().charAt(i) == '\n') {
//             yyline++;
//             yycolumn = 0; // Reseteamos columna en nueva línea
//         } else {
//             yycolumn++; // Solo incrementamos columna si no es un salto de línea
//         }
//     }
// }

// <YYINITIAL> . { 
//     int inicioColumna = yycolumn; 
//     yycolumn += yylength(); 
//     return new Token(TipoToken.NO_RECONOCIDO, yyline, inicioColumna, yytext());
// }
