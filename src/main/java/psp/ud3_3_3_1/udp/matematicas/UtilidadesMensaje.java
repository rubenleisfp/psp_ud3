package psp.ud3_3_3_1.udp.matematicas;

public class UtilidadesMensaje {

    public static String crearMensaje(String opArg, int num1Arg, int num2Arg) {
        StringBuilder sb = new StringBuilder();
        sb.append(opArg);
        sb.append(";");
        sb.append(num1Arg);
        sb.append(";");
        sb.append(num2Arg);
        sb.append(";");

        return sb.toString();
    }

    public static String procesarMensaje(String mensaje) {
        String[] mensajeArray = mensaje.split(";");
        String operacion = mensajeArray[0];
        String num1Str = mensajeArray[1];
        String num2Str = mensajeArray[2];

        int num1 = Integer.valueOf(num1Str);
        int num2 = Integer.valueOf(num2Str);

        int calculo = calcular(operacion, num1, num2);
        String resultado = "RESULTADO  " + calculo;
        return resultado;
    }

    public static int calcular(String operacion, int a, int b) {
        int resultado = 0;
        switch (operacion) {
            case "SUMAR":
                resultado = a + b;
            break;
            case "RESTAR":
                resultado = a + b;
            break;
            case "MULTIPLICAR":
                resultado = a + b;
            break;
            case "DIVIDIR":
                resultado = a + b;
            break;
            default:
                break;
        }
        return resultado;
    }


}
