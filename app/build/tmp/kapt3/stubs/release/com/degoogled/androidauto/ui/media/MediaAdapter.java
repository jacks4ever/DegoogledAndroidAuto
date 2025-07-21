package com.degoogled.androidauto.ui.media;

import java.lang.System;

/**
 * Adapter for the media list.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0010\u0011B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\u00020\u00062\n\u0010\t\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/degoogled/androidauto/ui/media/MediaAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/degoogled/androidauto/data/model/Media;", "Lcom/degoogled/androidauto/ui/media/MediaAdapter$MediaViewHolder;", "onItemClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MediaDiffCallback", "MediaViewHolder", "app_release"})
public final class MediaAdapter extends androidx.recyclerview.widget.ListAdapter<com.degoogled.androidauto.data.model.Media, com.degoogled.androidauto.ui.media.MediaAdapter.MediaViewHolder> {
    private final kotlin.jvm.functions.Function1<com.degoogled.androidauto.data.model.Media, kotlin.Unit> onItemClick = null;
    
    public MediaAdapter(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.degoogled.androidauto.data.model.Media, kotlin.Unit> onItemClick) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.degoogled.androidauto.ui.media.MediaAdapter.MediaViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.ui.media.MediaAdapter.MediaViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/degoogled/androidauto/ui/media/MediaAdapter$MediaViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/degoogled/androidauto/databinding/ItemMediaBinding;", "(Lcom/degoogled/androidauto/ui/media/MediaAdapter;Lcom/degoogled/androidauto/databinding/ItemMediaBinding;)V", "bind", "", "media", "Lcom/degoogled/androidauto/data/model/Media;", "app_release"})
    public final class MediaViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final com.degoogled.androidauto.databinding.ItemMediaBinding binding = null;
        
        public MediaViewHolder(@org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.databinding.ItemMediaBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.data.model.Media media) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/degoogled/androidauto/ui/media/MediaAdapter$MediaDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/degoogled/androidauto/data/model/Media;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"})
    static final class MediaDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.degoogled.androidauto.data.model.Media> {
        
        public MediaDiffCallback() {
            super();
        }
        
        @java.lang.Override
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.data.model.Media oldItem, @org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.data.model.Media newItem) {
            return false;
        }
        
        @java.lang.Override
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.data.model.Media oldItem, @org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.data.model.Media newItem) {
            return false;
        }
    }
}