package ma.cu.lalewicz;

import ma.cu.lalewicz.construction.element.ReflectorReversing;
import ma.cu.lalewicz.construction.element.Rotor;
import ma.cu.lalewicz.construction.element.RotorBlock;
import ma.cu.lalewicz.construction.element.SwitchBoard;
import ma.cu.lalewicz.construction.utils.Util;

public class Main {

    public static void main(String[] args) {

        char[] rotor_I =   {'E','K','M','F','L','G','D','Q','V','Z','N','T','O','W','Y','H','X','U','S','P','A','I','B','R','C','J'};
        int rotor_I_indentPosition = Util.toInt('R');
        char[] rotor_II =  {'A','J','D','K','S','I','R','U','X','B','L','H','W','T','M','C','Q','G','Z','N','P','Y','F','V','O','E'};
        int rotor_II_indentPosition = Util.toInt('F');
        char[] rotor_III = {'B','D','F','H','J','L','C','P','R','T','X','V','Z','N','Y','E','I','W','G','A','K','M','U','S','Q','O'};
        int rotor_III_indentPosition = Util.toInt('W');
        String dataReflectionB = "(AY) (BR) (CU) (DH) (EQ) (FS) (GL) (IP) (JX) (KN) (MO) (TZ) (VW)";
        String dataSwBoard = "(FR) (YE) (US) (QO) (FS) (GL) (KB)";
//        for (int i=0;i<26;i++)  System.out.println(i+"-"+Util.toChar(i));
        System.out.println("Enigma system");

        SwitchBoard switchBoard = new SwitchBoard(dataSwBoard);
        Rotor rotorR = new Rotor(rotor_I,22,rotor_I_indentPosition);
        Rotor rotorM = new Rotor(rotor_II,5,rotor_II_indentPosition);
        Rotor rotorL = new Rotor(rotor_III,22,rotor_III_indentPosition);
        ReflectorReversing invertReflector = new ReflectorReversing(dataReflectionB);
        RotorBlock rotorsBlock = new RotorBlock(switchBoard,rotorR,rotorM,rotorL,invertReflector);

        String plainText = "ALAMAKOTAAKOTMAALETOJESTWIADOMOSCDLAUBOOTAODBOOTA";
        String secretText = "";
        for (int i = 0; i<plainText.length();i++){
            secretText = secretText+rotorsBlock.encodeChar(plainText.charAt(i));
        }

        System.out.println(secretText);
        System.out.println("------------------------------------------");
        rotorsBlock.resetPositionsRotors();
        plainText = "";
        for (int i = 0; i<secretText.length();i++){
            plainText = plainText + rotorsBlock.encodeChar(secretText.charAt(i));
        }
        System.out.println(plainText);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");

        plainText = "jestem  roman z pacanowa czesto sam podbijam swoje pilki na czarnym rynku w miejscowosi wawa";
        rotorsBlock.resetPositionsRotors();
        secretText = rotorsBlock.encodeStr(plainText);
        System.out.println(secretText);
        System.out.println("*****************************************");
        rotorsBlock.resetPositionsRotors();
        plainText = rotorsBlock.encodeStr(secretText);
        System.out.println(plainText);



//pierwsza proba

//        List<Integer> encrypted = new ArrayList<>();
//
//        for (int i = 5; i<12; i++){
//            System.out.print("Litera "+ Util.toChar(i)+" <bI> ");
//            rotorR.moveRotorAndReturnIndent();
//            int charInt = rotorR.encryptForward(i);
//            System.out.print(Util.toChar(charInt)+"  <Odwr> ");
//            charInt = invertReflector.encode(charInt);
//            System.out.print(Util.toChar(charInt)+" <bI> ");
//            charInt = rotorR.encryptBackward(charInt);
//            System.out.println(Util.toChar(charInt)+" pozycja wirnika : "+rotorR.getPositionRotor());
//            encrypted.add(charInt);
//        }
//        System.out.println("Reset pozycji wirnika i deszyfracja");
//        rotorR.setPositionRotor(22);
//        for (int i:encrypted) {
//            System.out.print("Litera "+ Util.toChar(i)+" <bI> ");
//            rotorR.moveRotorAndReturnIndent();
//            int charInt = rotorR.encryptForward(i);
//            System.out.print(Util.toChar(charInt)+"  <Odwr> ");
//            charInt = invertReflector.encode(charInt);
//            System.out.print(Util.toChar(charInt)+" <bI> ");
//            System.out.println(Util.toChar(rotorR.encryptBackward(charInt))+" pozycja wirnika : "+rotorR.getPositionRotor());
//        }




    }
}
