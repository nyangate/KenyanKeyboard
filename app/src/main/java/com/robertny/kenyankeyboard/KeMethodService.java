package com.robertny.kenyankeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import java.util.Arrays;
import java.util.List;

/**
 * Created by robertnyangate on 23/08/16.
 */
public class KeMethodService extends InputMethodService
    implements KeyboardView.OnKeyboardActionListener

    {

        private KeyboardView kv;
        private Keyboard keyboard;

        private boolean caps = false;
        @Override
        public View onCreateInputView() {
            kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
            keyboard = new Keyboard(this, R.xml.kwerty);
            kv.setKeyboard(keyboard);
            kv.setOnKeyboardActionListener(this);
            return kv;
        }
        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            InputConnection ic = getCurrentInputConnection();
            switch(primaryCode){
                case Keyboard.KEYCODE_DELETE :
                    ic.deleteSurroundingText(1, 0);
                    break;
                case Keyboard.KEYCODE_SHIFT:
                    caps = !caps;
                    keyboard.setShifted(caps);
                    kv.invalidateAllKeys();
                    break;
                case Keyboard.KEYCODE_DONE:
                    ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                    break;
                case 730:
                    keyboard = new Keyboard(this, R.xml.denominational);
                    kv.setKeyboard(keyboard);
                    kv.setOnKeyboardActionListener(this);
                    break;

                case 55004:
                    keyboard = new Keyboard(this, R.xml.kwerty);
                    kv.setKeyboard(keyboard);
                    kv.setOnKeyboardActionListener(this);
                    break;
                case 1000:
                    ic.commitText("1000",1);
                    break;
                case 500:
                    ic.commitText("500",1);
                    break;
                case 200:
                    ic.commitText("200",1);
                    break;
                case 50:
                    ic.commitText("50",1);
                    break;
                case 40:
                    ic.commitText("40",1);
                    break;
                case 20:
                    ic.commitText("20",1);
                    break;
                case 10:
                    ic.commitText("10",1);
                    break;
                case 5:
                    ic.commitText("5",1);
                    break;
                case 0:
                    ic.commitText("0",1);
                    break;
                case 1010:
                    ic.commitText("/",1);
                    break;
                case 1011:
                    ic.commitText("=",1);
                    break;
                case 1012:
                    ic.commitText("-",1);
                    break;
                case 1013:
                    ic.commitText("+",1);
                    break;
                case 1014:
                    ic.commitText("*",1);
                    break;
                case 1016:
                    ic.commitText("\'",1);
                    break;
                case 1015:
                    ic.commitText("100",1);
                    break;
                case 1017:
                    ic.commitText(".",1);
                    break;
                case 1018:
                    ic.commitText("[",1);
                    break;
                case 1019:
                    ic.commitText("]",1);
                    break;
                case 1020:
                    ic.commitText("{",1);
                    break;
                case 1021:
                    ic.commitText("}",1);
                    break;
                case 1022:
                    ic.commitText("%",1);
                    break;
                case 32:
                    ic.commitText(" ",1);
                    break;
                case 63:
                    ic.commitText("?",1);
                    break;
                case 33:
                    ic.commitText("!",1);
                    break;
                case 58:
                    ic.commitText(":",1);
                    break;
                case 59:
                    ic.commitText(";",1);
                    break;
                case 122:
                    ic.commitText("z",1);
                    break;
                default:
                    if(primaryCode>0 && primaryCode<10)
                    ic.commitText(""+primaryCode,1);
                    else{
                        char code = (char)primaryCode;
                        if(Character.isLetter(code) && caps){
                            code = Character.toUpperCase(code);
                        }
                        ic.commitText(String.valueOf(code),1);
                    }

                    break;
            }
        }

        @Override
        public void onPress(int primaryCode) {
            Log.d("KEY","pressed>>>"+primaryCode);
        }

        @Override
        public boolean onKeyLongPress(int keyCode, KeyEvent event) {

            while (event.isLongPress())
            onKey(keyCode,new int[event.getKeyCode()]);
            return true;
        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onText(CharSequence text) {
            Log.d("KEY","pressed>>>"+text);
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void swipeUp() {

        }

        @Override
        public void swipeLeft() {
            keyboard = new Keyboard(this, R.xml.kwerty);
            kv.setKeyboard(keyboard);
            kv.setOnKeyboardActionListener(this);
        }

        @Override
        public void swipeRight() {
            keyboard = new Keyboard(this, R.xml.denominational);
            kv.setKeyboard(keyboard);
            kv.setOnKeyboardActionListener(this);
        }


        @Override
        public void onDestroy() {
            super.onDestroy();
            keyboard = new Keyboard(this, R.xml.kwerty);
            kv.setKeyboard(keyboard);
            kv.setOnKeyboardActionListener(this);
        }


    }
