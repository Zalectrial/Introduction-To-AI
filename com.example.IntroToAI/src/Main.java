/*
 * Copyright (c) 2017. Robyn Van Deventer.
 * Created for Swinburne University of Technology.
 */

import Inference.BackwardChaining;
import Inference.ForwardChaining;
import Inference.Method;
import Inference.TruthTable;

public class Main {

    public static void main (String[] args) {

        Method method = Method.valueOf(args[0]);
        KBReader reader = new KBReader(args[1]);
        String tell = reader.getTell();
        String ask = reader.getAsk();

        System.out.println(method.toString());
        System.out.println(tell);
        System.out.println(ask);

        switch (method) {
            case BC: new BackwardChaining(tell, ask);
                break;
            case FC: new ForwardChaining(tell, ask);
                break;
            case TT: new TruthTable(tell, ask);
                break;
        }
    }
}
