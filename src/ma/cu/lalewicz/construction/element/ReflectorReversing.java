package ma.cu.lalewicz.construction.element;

import ma.cu.lalewicz.construction.model.SimpleSwitch;

public class ReflectorReversing implements SimpleSwitch {

    //(AY) (BR) (CU) (DH) (EQ) (FS) (GL) (IP) (JX) (KN) (MO) (TZ) (VW)

    private int[] tableWire = new int[26];

    public ReflectorReversing(String dataRef) {
            String[] tableRef = dataRef.split(" ");
//        System.out.println();
//        for (String element:tableRef) {
//            System.out.print(element+", ");
//        }
//        System.out.println();
        for (String element:tableRef){
            int index1 = element.codePointAt(1)-65;
            int index2 = element.codePointAt(2)-65;
            this.tableWire[index1] = index2;
            this.tableWire[index2] = index1;
          }
//        for (int i=0;i<this.tableWire.length; i++ ) {
//            System.out.print(i+" - "+this.tableWire[i]+", ");
//        }
    }

    @Override
    public int encode(int position) {
        return tableWire[position];
    }



}
