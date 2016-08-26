package com.robertny.kenyankeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

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
                default:
                    char code = (char)primaryCode;
                    if(Character.isLetter(code) && caps){
                        code = Character.toUpperCase(code);
                    }
                    ic.commitText(String.valueOf(code),1);
            }
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public boolean onKeyLongPress(int keyCode, KeyEvent event) {
            while (event.isLongPress())
            onKey(keyCode,new int[event.getKeyCode()]);
            return super.onKeyLongPress(keyCode, event);
        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onText(CharSequence text) {
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
