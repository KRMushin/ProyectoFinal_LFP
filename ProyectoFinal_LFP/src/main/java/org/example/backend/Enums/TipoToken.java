package org.example.backend.Enums;

import javax.sound.sampled.BooleanControl;
import java.awt.*;

public enum TipoToken {

    CREATE {
        @Override
        public Color obtenerColor() {
            return Color.ORANGE;
        }
    },
    TIPO_DE_DATO {
        @Override
        public Color obtenerColor() {
            return new Color(128, 0, 128);
        }
    },
    ENTERO {
        @Override
        public Color obtenerColor() {
            return Color.BLUE;
        }
    },
    DECIMAL {
        @Override
        public Color obtenerColor() {
            return Color.BLUE;
        }
    },
    FECHA {
        @Override
        public Color obtenerColor() {
            return Color.YELLOW;
        }
    },
    CADENA {
        @Override
        public Color obtenerColor() {
            return Color.GREEN;
        }
    },
    IDENTIFICADOR {
        @Override
        public Color obtenerColor() {
            return new Color(255, 0, 255);
        }
    },
    BOOLEANO {
        @Override
        public Color obtenerColor() {
            return Color.BLUE;
        }
    },
    FUNCION_AGREGACION {
        @Override
        public Color obtenerColor() {
            return Color.BLUE;
        }
    },
    SIGNOS {
        @Override
        public Color obtenerColor() {
            return Color.BLACK;
        }
    },
    ARITMETICO {
        @Override
        public Color obtenerColor() {
            return Color.BLACK;
        }
    },
    RELACIONAL {
        @Override
        public Color obtenerColor() {
            return Color.BLACK;
        }
    },
    LOGICO {
        @Override
        public Color obtenerColor() {
            return Color.ORANGE;
        }
    },
    COMENTARIO_LINEA {
        @Override
        public Color obtenerColor() {
            return Color.GRAY;
        }
    },
    NO_RECONOCIDO {
        @Override
        public Color obtenerColor() {
            return Color.RED;
        }
    },
    EOF {
        @Override
        public Color obtenerColor() {
            return Color.ORANGE;
        }
    },
    COMENTARIO {
        @Override
        public Color obtenerColor() {
            return Color.gray;
        }
    },
    ESPACIO_BLANCO {
        @Override
        public Color obtenerColor() {
            return Color.gray;
        }
    }, LlaveEspecial {
        @Override
        public Color obtenerColor() {
            return Color.ORANGE;
        }
    },TIPO_DE_DATO_BOOLEANO{
        @Override
        public Color obtenerColor() {
            return new Color(128, 0, 128);
        }
        
    },TIPO_DE_DATO_NUMERICO {
        @Override
        public Color obtenerColor() {
            return new Color(128, 0, 128);
        }
    },TIPO_DE_DATO_TEXTO {
        @Override
        public Color obtenerColor() {
            return new Color(128, 0, 128);
        }
    };


    public abstract Color obtenerColor();

}
