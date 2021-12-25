package ma.cu.lalewicz.construction.element;

import ma.cu.lalewicz.construction.utils.Util;

public class RotorBlock {
    private SwitchBoard switchBoard; //przelacznica wtyczkowa
    private Rotor rotorRight; //rotor prawy pierwszy
    private Rotor rotorMiddle; //rotor srodkowy drugi
    private Rotor rotorLeft; //rotor lewy trzeci
    private ReflectorReversing reflectorRev; // bębenek obracający

    private int startPosRotorRight;
    private int startPosRotorMiddle;
    private int startPosRotorLeft;


    public RotorBlock(SwitchBoard switchBoard, Rotor rotorRight, Rotor rotorMiddle, Rotor rotorLeft, ReflectorReversing reflectorRev) {
        this.switchBoard = switchBoard;
        this.rotorRight = rotorRight;
        this.rotorMiddle = rotorMiddle;
        this.rotorLeft = rotorLeft;
        this.reflectorRev = reflectorRev;
        this.startPosRotorRight = rotorRight.getPositionRotor();
        this.startPosRotorMiddle = rotorMiddle.getPositionRotor();
        this.startPosRotorLeft = rotorLeft.getPositionRotor();
    }

    public char encodeChar(char character) {
        int charInt = Util.toInt(character);
        System.out.print("Lit: "+Util.toChar(charInt)+" sb ");
        charInt = switchBoard.encode(charInt);
        System.out.print(" "+Util.toChar(charInt)+" w1<:");
        if (rotorRight.moveRotorAndReturnIndent()){
            if(rotorMiddle.moveRotorAndReturnIndent()){
                rotorLeft.moveRotorAndReturnIndent();
            }
        }
        System.out.print(rotorRight.getPositionRotor());
        charInt = rotorRight.encryptForward(charInt);
        System.out.print(" "+Util.toChar(charInt)+" w2<:"+ rotorMiddle.getPositionRotor()+" ");
        charInt = rotorMiddle.encryptForward(charInt);
        System.out.print(" "+Util.toChar(charInt)+" w3<:"+ rotorLeft.getPositionRotor()+" ");
        charInt = rotorLeft.encryptForward(charInt);
        System.out.print(" "+Util.toChar(charInt)+" rev ");
        charInt = reflectorRev.encode(charInt);
        System.out.print(" "+Util.toChar(charInt)+" w3>:"+ rotorLeft.getPositionRotor()+" ");
        charInt = rotorLeft.encryptBackward(charInt);
        System.out.print(" "+Util.toChar(charInt)+" w2>:"+ rotorMiddle.getPositionRotor()+" ");
        charInt = rotorMiddle.encryptBackward(charInt);
        System.out.print(" "+Util.toChar(charInt)+" w1>:"+ rotorRight.getPositionRotor()+ " ");
        charInt = rotorRight.encryptBackward(charInt);
        System.out.print(Util.toChar(charInt)+" sb ");
        charInt = switchBoard.encode(charInt);
        System.out.println(Util.toChar(charInt));
        return Util.toChar(charInt);
    }

    public String encodeStr(String text){
        text = text.toUpperCase().replace(" ","");
        String secretText = "";
        for (int i = 0; i<text.length();i++){
            secretText = secretText + encodeChar(text.charAt(i));
        }
        return secretText;
    }

    public void resetPositionsRotors(){
        rotorRight.setPositionRotor(startPosRotorRight);
        rotorMiddle.setPositionRotor(startPosRotorMiddle);
        rotorLeft.setPositionRotor(startPosRotorLeft);
    }


}
