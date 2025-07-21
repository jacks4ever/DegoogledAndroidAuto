package com.degoogled.androidauto.ui.navigation;

import java.lang.System;

/**
 * ViewModel for the navigation screen.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010%\u001a\u00020&H\u0002J\u0006\u0010\'\u001a\u00020&J\u000e\u0010(\u001a\u00020&2\u0006\u0010)\u001a\u00020\u000fJ\u000e\u0010*\u001a\u00020&2\u0006\u0010+\u001a\u00020\u000bJ\u0006\u0010,\u001a\u00020&J\u0006\u0010-\u001a\u00020&R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u00110\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00140\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0019\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00110\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00140\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0019\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019\u00a8\u0006."}, d2 = {"Lcom/degoogled/androidauto/ui/navigation/NavigationViewModel;", "Landroidx/lifecycle/ViewModel;", "searchLocationUseCase", "Lcom/degoogled/androidauto/domain/usecase/SearchLocationUseCase;", "calculateRouteUseCase", "Lcom/degoogled/androidauto/domain/usecase/CalculateRouteUseCase;", "(Lcom/degoogled/androidauto/domain/usecase/SearchLocationUseCase;Lcom/degoogled/androidauto/domain/usecase/CalculateRouteUseCase;)V", "_currentInstruction", "Landroidx/lifecycle/MutableLiveData;", "Lcom/degoogled/androidauto/data/model/Instruction;", "_currentLocation", "Lcom/degoogled/androidauto/data/model/Location;", "_currentRoute", "Lcom/degoogled/androidauto/data/model/Route;", "_errorMessage", "", "_isNavigating", "", "kotlin.jvm.PlatformType", "_searchResults", "", "_selectedDestination", "currentInstruction", "Landroidx/lifecycle/LiveData;", "getCurrentInstruction", "()Landroidx/lifecycle/LiveData;", "currentLocation", "getCurrentLocation", "currentRoute", "getCurrentRoute", "errorMessage", "getErrorMessage", "isNavigating", "searchResults", "getSearchResults", "selectedDestination", "getSelectedDestination", "calculateRoute", "", "centerOnCurrentLocation", "searchLocation", "query", "selectDestination", "location", "startNavigation", "stopNavigation", "app_release"})
public final class NavigationViewModel extends androidx.lifecycle.ViewModel {
    private final com.degoogled.androidauto.domain.usecase.SearchLocationUseCase searchLocationUseCase = null;
    private final com.degoogled.androidauto.domain.usecase.CalculateRouteUseCase calculateRouteUseCase = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.degoogled.androidauto.data.model.Location>> _searchResults = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.degoogled.androidauto.data.model.Location>> searchResults = null;
    private final androidx.lifecycle.MutableLiveData<com.degoogled.androidauto.data.model.Location> _selectedDestination = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.degoogled.androidauto.data.model.Location> selectedDestination = null;
    private final androidx.lifecycle.MutableLiveData<com.degoogled.androidauto.data.model.Location> _currentLocation = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.degoogled.androidauto.data.model.Location> currentLocation = null;
    private final androidx.lifecycle.MutableLiveData<com.degoogled.androidauto.data.model.Route> _currentRoute = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.degoogled.androidauto.data.model.Route> currentRoute = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isNavigating = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isNavigating = null;
    private final androidx.lifecycle.MutableLiveData<com.degoogled.androidauto.data.model.Instruction> _currentInstruction = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.degoogled.androidauto.data.model.Instruction> currentInstruction = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> errorMessage = null;
    
    public NavigationViewModel(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.domain.usecase.SearchLocationUseCase searchLocationUseCase, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.domain.usecase.CalculateRouteUseCase calculateRouteUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.degoogled.androidauto.data.model.Location>> getSearchResults() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.degoogled.androidauto.data.model.Location> getSelectedDestination() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.degoogled.androidauto.data.model.Location> getCurrentLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.degoogled.androidauto.data.model.Route> getCurrentRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isNavigating() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.degoogled.androidauto.data.model.Instruction> getCurrentInstruction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getErrorMessage() {
        return null;
    }
    
    public final void searchLocation(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    public final void selectDestination(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Location location) {
    }
    
    private final void calculateRoute() {
    }
    
    public final void startNavigation() {
    }
    
    public final void stopNavigation() {
    }
    
    public final void centerOnCurrentLocation() {
    }
}