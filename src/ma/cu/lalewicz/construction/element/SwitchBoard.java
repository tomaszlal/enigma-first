package ma.cu.lalewicz.construction.element;

import ma.cu.lalewicz.construction.model.SimpleSwitch;

public class SwitchBoard implements SimpleSwitch {

    private int[] tableWire = new int[26];

    public SwitchBoard(String dataRef) {
        //wypelnia tabele zgodnie z waroscia indeksu
        for (int i=0;i<26;i++) this.tableWire[i]=i;
        //wypelnia tabele zamianami zgodnie z wartoscia dataRef
        String[] tableRef = dataRef.split(" ");
        for (String element:tableRef){
            int index1 = element.codePointAt(1)-65;
            int index2 = element.codePointAt(2)-65;
            this.tableWire[index1] = index2;
            this.tableWire[index2] = index1;
        }

    }

    @Override
    public int encode(int position) {
        return this.tableWire[position];
    }
}
