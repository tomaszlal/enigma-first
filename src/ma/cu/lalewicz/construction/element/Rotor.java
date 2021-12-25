package ma.cu.lalewicz.construction.element;

public class Rotor {

    private int positionRotor;      //przesunięcie rotora w stosunku do oryginalnych pozycji liter
    private int indentationPosition;   //pozycja wcięcia rotora przestawiającego nastepny wirnik
    private boolean indent = false;

    private int[] tableInputWire = new int[26];  //tabela przedstawiająca połączenia i dokonujca kodowania w przód
    private int[] tableOutputWire = new int[26]; //tabela przedstawiająca połączenia i dokonujca kodowania w tył

    public Rotor(char[] table,int startPosRotor, int indentPos) {
        this.positionRotor = startPosRotor;
        this.indentationPosition = indentPos;
        for (int i = 0; i < 26; i++) {
            this.tableInputWire[i] = (table[i])-65;
            this.tableOutputWire[(table[i])-65] = i;
        }
//        System.out.println(this.forwardWire);
//        System.out.println(this.backwardWire);
//        for (int element:tableInputWire) {
//            System.out.print(element+", ");
//        }
//        System.out.println();
//        for (int element:tableOutputWire) {
//            System.out.print(element+", ");
//        }
//        System.out.println();
    }

    //seter do ustawienia pozycji
    public void setPositionRotor(int positionRotor) {
        this.positionRotor = positionRotor;
    }

    //geter do pobrania aktualnej pozycji rotora
    public int getPositionRotor() {
        return positionRotor;
    }

    // kodowanie litery do przodu - czyli pierwsze użycie wirnika
    public int encryptForward(int position){
        int absolutePosWire = (position + positionRotor) % 26;
        return this.tableInputWire[absolutePosWire];
    }

    //konowanie litery do tyłu - czyli drugie użycie wirnika
    public int encryptBackward(int position){
        return ((this.tableOutputWire[position]-positionRotor)+26)%26;
    }

    //pobież wartość  liczbową wcięcia na wirniku - w której pozycji ma obrócić następny wirnik
    public int getIndentationPosition() {
        return indentationPosition;
    }

    // obróć wirnik -przesuń do następnej pozycji
    public boolean moveRotorAndReturnIndent(){
        checkIndent();
        this.positionRotor++;
        this.positionRotor= this.positionRotor % 26;
        return this.indent;
    }

    //sprawdzenie i ustawienie zmiennej "indent" jaku true, co oznacza że należy przekręcić następny wirnik
    private void checkIndent(){
        if (this.positionRotor == this.indentationPosition){
            this.indent = true;
        } else {
            this.indent = false;
        }
    }
}
