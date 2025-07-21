package com.degoogled.androidauto.data.repository;

import java.lang.System;

/**
 * Enum representing the state of media playback.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/degoogled/androidauto/data/repository/PlaybackState;", "", "(Ljava/lang/String;I)V", "PLAYING", "PAUSED", "STOPPED", "LOADING", "ERROR", "app_release"})
public enum PlaybackState {
    /*public static final*/ PLAYING /* = new PLAYING() */,
    /*public static final*/ PAUSED /* = new PAUSED() */,
    /*public static final*/ STOPPED /* = new STOPPED() */,
    /*public static final*/ LOADING /* = new LOADING() */,
    /*public static final*/ ERROR /* = new ERROR() */;
    
    PlaybackState() {
    }
}