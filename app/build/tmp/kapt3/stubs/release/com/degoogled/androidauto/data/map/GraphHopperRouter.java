package com.degoogled.androidauto.data.map;

import java.lang.System;

/**
 * Router implementation using GraphHopper for offline routing.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J<\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0002\u00a2\u0006\u0002\u0010\u0014J\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0007\u0082\u0002\u000f\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lcom/degoogled/androidauto/data/map/GraphHopperRouter;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "graphHopper", "error/NonExistentClass", "Lerror/NonExistentClass;", "calculateRoute", "Lkotlin/Result;", "Lcom/degoogled/androidauto/data/model/Route;", "start", "Lcom/degoogled/androidauto/data/model/Location;", "end", "profile", "", "calculateRoute-BWLJW6A", "(Lcom/degoogled/androidauto/data/model/Location;Lcom/degoogled/androidauto/data/model/Location;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initialize", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "release", "", "Companion", "app_release"})
public final class GraphHopperRouter {
    private final android.content.Context context = null;
    private error.NonExistentClass graphHopper;
    @org.jetbrains.annotations.NotNull
    public static final com.degoogled.androidauto.data.map.GraphHopperRouter.Companion Companion = null;
    private static final java.lang.String TAG = "GraphHopperRouter";
    
    public GraphHopperRouter(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object initialize(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    public final void release() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/degoogled/androidauto/data/map/GraphHopperRouter$Companion;", "", "()V", "TAG", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}