package com.example.jais.chessboard;

import java.util.HashMap;

/**
 * Created by jais on 5/28/2016.
 */
public class Piece {
    public HashMap<Integer, Integer> getData() {
        return data;
    }

    public void setData(HashMap<Integer, Integer> data) {
        this.data = data;
    }

    private HashMap<Integer, Integer> data = new HashMap<>();

    public Piece(){
        data.put(1,R.drawable.blackbishop);
        data.put(8, R.drawable.blackking);
    }

    public Piece(String [] pieceLocation){
        int [][] matrixLocation = new int[8][8];
        int inc = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                matrixLocation[i][j] = inc;
                inc++;
            }
        }
        for(int i = 0; i < pieceLocation.length; i++) {
            data.put(getPieceLocation(pieceLocation[i],matrixLocation),getResourcePice(pieceLocation[i]));
        }
    }

    public int getNumberLocation(String location){
        int number = 0;
        String [] temp = location.split(",");
        if(temp[2].equals("8")) {
            number = 0;
        }else if(temp[2].equals("7")) {
            number = 1;
        }else if(temp[2].equals("6")) {
            number = 2;
        }else if(temp[2].equals("5")) {
            number = 3;
        }else if(temp[2].equals("4")) {
            number = 4;
        }else if(temp[2].equals("3")) {
            number = 5;
        }else if(temp[2].equals("1")) {
            number = 6;
        }else{
            number = 7;
        }

        return number;
    }

    public int getColumnNum(String location) {
        int colnum = 0;
        String [] temp = location.split(",");
        if(temp[1].equals("a")) {
            colnum = 0;
        }else if(temp[1].equals("b")) {
            colnum = 1;
        }else if(temp[1].equals("c")) {
            colnum = 2;
        }else if(temp[1].equals("d")) {
            colnum = 3;
        }else if(temp[1].equals("e")) {
            colnum = 4;
        }else if(temp[1].equals("f")) {
            colnum = 5;
        }else if(temp[1].equals("g")) {
            colnum = 6;
        }else if(temp[1].equals("h")) {
            colnum = 7;
        }

        return colnum;
    }

    public int getPieceLocation(String location, int [][] matloc) {
        int pl;
        pl = matloc[getNumberLocation(location)][getColumnNum(location)];
        return pl;
    }

    public int getResourcePice(String location) {
        int resource = 0;
        String [] temp = location.split(",");
        if(temp[0].equals("K")) {
            resource = R.drawable.whiteking;
        }else if(temp[0].equals("Q")) {
            resource = R.drawable.whitequeen;
        }else if(temp[0].equals("B")) {
            resource = R.drawable.whitebishop;
        }else if(temp[0].equals("R")) {
            resource = R.drawable.whiterook;
        }else if(temp[0].equals("k")) {
            resource = R.drawable.blackking;
        }else if(temp[0].equals("q")) {
            resource = R.drawable.blackqueen;
        }else if(temp[0].equals("b")) {
            resource = R.drawable.blackbishop;
        }else {
            resource = R.drawable.blackrook;
        }

        return  resource;
    }
}
