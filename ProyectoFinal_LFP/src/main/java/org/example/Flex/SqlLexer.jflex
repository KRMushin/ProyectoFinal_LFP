import java_cup.runtime.Symbol;
package org.example.Flex;
import org.example.backend.Enums.TipoToken;
import org.example.backend.Tokens.Token;
%%

/* Definición de la clase del analizador léxico */
%public
%class AnalizadorLexico
%unicode
%line
%column
%caseless

/* Definición de patrones regulares */
   Digito = [0-9]
   FECHA = "'"{Digito}{Digito}{Digito}{Digito}"-"{Digito}{Digito}"-"{Digito}{Digito}"'"
   DECIMAL = "-?" {Digito}+ "(\\." {Digito}+ ")?"
   ERROR_DECIMAL = "-?({Digito}*\\.){2,}{Digito}*"
   NUMERO = "-"?[0-9]+
   Letra = [a-zA-Z]
   LetterLower = [a-z]
   Identificador = {LetterLower}({LetterLower}|{Digito}|_{LetterLower}|_{Digito})*
   CADENA = "'"( [^'] | "''" )*"'" //cadenas de texto
   PALABRA = [a-zA-Z]+
   SPACE = " "
%%

/* Definición de reglas de tokens */
/* Entero , decimal */

 {FECHA}          { return new Token(TipoToken.FECHA, yyline, yycolumn, yytext()); }
  {ERROR_DECIMAL}  { return new Token(TipoToken.NO_RECONOCIDO, yyline, yycolumn, yytext()); }
 {DECIMAL}        { return new Token(TipoToken.DECIMAL, yyline, yycolumn, yytext()); }
 {NUMERO}         { return new Token(TipoToken.ENTERO,  yyline, yycolumn, yytext()); }
 {CADENA}          { return new Token(TipoToken.CADENA,  yyline, yycolumn, yytext()); }

/* LLAVE ESPECIAL */
 
"PRIMARY KEY"  { return new Token(TipoToken.LlaveEspecial,  yyline, yycolumn, yytext()); }
"NOT NULL" { return new Token(TipoToken.LlaveEspecial,  yyline, yycolumn, yytext()); }
"UNIQUE" { return new Token(TipoToken.LlaveEspecial,  yyline, yycolumn, yytext()); }
"AUTO INCREMENT" { return new Token(TipoToken.LlaveEspecial,  yyline, yycolumn, yytext()); }

"PRIMARY_KEY"  { return new Token(TipoToken.LlaveEspecial,  yyline, yycolumn, yytext()); }
"NOT_NULL" { return new Token(TipoToken.LlaveEspecial,  yyline, yycolumn, yytext()); }
"UNIQUE" { return new Token(TipoToken.LlaveEspecial,  yyline, yycolumn, yytext()); }
"AUTO_INCREMENT" { return new Token(TipoToken.LlaveEspecial,  yyline, yycolumn, yytext()); }


/*    TOKENS CREATE */
"CREATE"          { return new Token(TipoToken.CREATE, yyline, yycolumn,yytext()); }
"DATABASE"        { return new Token(TipoToken.CREATE, yyline, yycolumn,yytext()); }
"TABLE"           { return new Token(TipoToken.CREATE, yyline, yycolumn,yytext()); }
"KEY"             { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"NULL"            { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"PRIMARY"         { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"UNIQUE"          { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"FOREIGN"         { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"REFERENCES"      { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"ALTER"           { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"ADD"             { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"COLUMN"          { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"TYPE"            { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"DROP"            { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"CONSTRAINT"      { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"IF"              { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"EXIST"           { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"CASCADE"         { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"ON"              { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"DELETE"          { return new Token(TipoToken.CREATE, yyline, yycolumn,yytext()); }
"SET"             { return new Token(TipoToken.CREATE, yyline, yycolumn,yytext()); }
"UPDATE"          { return new Token(TipoToken.CREATE, yyline, yycolumn,yytext()); }
"INSERT"          { return new Token(TipoToken.CREATE, yyline, yycolumn,yytext()); }
"INTO"            { return new Token(TipoToken.CREATE, yyline, yycolumn,yytext()); }
"VALUES"          { return new Token(TipoToken.CREATE, yyline, yycolumn,yytext()); }
"SELECT"          { return new Token(TipoToken.CREATE, yyline, yycolumn,yytext()); }
"FROM"            { return new Token(TipoToken.CREATE, yyline, yycolumn,yytext()); }
"WHERE"           { return new Token(TipoToken.CREATE, yyline, yycolumn,yytext()); }
"AS"              { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"GROUP"           { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"ORDER"           { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"BY"              { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"ASC"             { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"DESC"            { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"LIMIT"           { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"JOIN"            { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"DEFAULT"         { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
"EXISTS"          { return new Token(TipoToken.CREATE, yyline, yycolumn, yytext()); }
/* Tipos de datos */
"INTEGER"         { return new Token(TipoToken.TIPO_DE_DATO, yyline, yycolumn, yytext()); }
"BIGINT"          { return new Token(TipoToken.TIPO_DE_DATO, yyline, yycolumn, yytext()); }
"VARCHAR"         { return new Token(TipoToken.TIPO_DE_DATO, yyline, yycolumn, yytext()); }
"DECIMAL"         { return new Token(TipoToken.TIPO_DE_DATO, yyline, yycolumn, yytext()); }
"NUMERIC"         { return new Token(TipoToken.TIPO_DE_DATO, yyline, yycolumn, yytext()); }
"INT"             { return new Token(TipoToken.TIPO_DE_DATO, yyline, yycolumn, yytext()); }
"SERIAL"          { return new Token(TipoToken.TIPO_DE_DATO, yyline, yycolumn, yytext()); }

"DATE"            { return new Token(TipoToken.TIPO_DE_DATO, yyline, yycolumn, yytext()); }

"TEXT"            { return new Token(TipoToken.TIPO_DE_DATO, yyline, yycolumn, yytext()); }

"BOOLEAN"         { return new Token(TipoToken.TIPO_DE_DATO, yyline, yycolumn, yytext()); }



/* Booleanos */
 "TRUE"            { return new Token(TipoToken.BOOLEANO, yyline, yycolumn, yytext()); }
 "FALSE"           { return new Token(TipoToken.BOOLEANO, yyline, yycolumn, yytext()); }

/* Funcion Agregacion */
 "SUM"             { return new Token(TipoToken.FUNCION_AGREGACION, yyline, yycolumn, yytext()); }
 "AVG"             { return new Token(TipoToken.FUNCION_AGREGACION, yyline, yycolumn, yytext()); }
 "COUNT"           { return new Token(TipoToken.FUNCION_AGREGACION, yyline, yycolumn, yytext()); }
 "MAX"             { return new Token(TipoToken.FUNCION_AGREGACION, yyline, yycolumn, yytext()); }
 "MIN"             { return new Token(TipoToken.FUNCION_AGREGACION, yyline, yycolumn, yytext()); }

/* signos*/
 "("               { return new Token(TipoToken.SIGNOS, yyline, yycolumn, yytext()); }
 ")"               { return new Token(TipoToken.SIGNOS, yyline, yycolumn, yytext()); }
 ","               { return new Token(TipoToken.SIGNOS, yyline, yycolumn, yytext()); }
 ";"               { return new Token(TipoToken.SIGNOS, yyline, yycolumn, yytext()); }
 "."               { return new Token(TipoToken.SIGNOS, yyline, yycolumn, yytext()); }
 "="               { return new Token(TipoToken.SIGNOS, yyline, yycolumn, yytext()); }

"--".*  { return new Token(TipoToken.COMENTARIO, yyline, yycolumn, yytext()); }

/* Aritmeticos*/
 "+"               { return new Token(TipoToken.ARITMETICO, yyline, yycolumn, yytext()); }
 "-"               { return new Token(TipoToken.ARITMETICO, yyline, yycolumn, yytext()); }
 "*"               { return new Token(TipoToken.ARITMETICO, yyline, yycolumn, yytext()); }
 "/"               { return new Token(TipoToken.ARITMETICO, yyline, yycolumn, yytext()); }

/* relacionales*/
 "<"               { return new Token(TipoToken.RELACIONAL, yyline, yycolumn, yytext()); }
 ">"               { return new Token(TipoToken.RELACIONAL, yyline, yycolumn, yytext()); }
 "<="              { return new Token(TipoToken.RELACIONAL, yyline, yycolumn, yytext()); }
 ">="              { return new Token(TipoToken.RELACIONAL, yyline, yycolumn, yytext()); }
 
 /* logicos */
 "AND"             { return new Token(TipoToken.LOGICO, yyline, yycolumn, yytext()); }
 "OR"              { return new Token(TipoToken.LOGICO, yyline, yycolumn, yytext()); }
 "NOT"             { return new Token(TipoToken.LOGICO, yyline, yycolumn, yytext()); }


/* Identificadores */
{Identificador}   { return new Token(TipoToken.IDENTIFICADOR, yyline, yycolumn, yytext()); }

/* Comentarios (Ejemplo de comentarios de línea SQL) */

/* Ignorar espacios en blanco y saltos de línea */
[\t\n\r ]+        { /* Ignorar espacios y saltos de línea */ }

[a-zA-Z]+[A-Z0-9_]*[a-zA-Z0-9]* { return new Token(TipoToken.NO_RECONOCIDO, yyline, yycolumn, yytext()); }

.                 { return new Token(TipoToken.NO_RECONOCIDO, yyline, yycolumn, yytext()); }

/* EOF */
<<EOF>> { return new Token(TipoToken.EOF, yyline, yycolumn, "EOF"); }
