package com.degoogled.androidauto.ui.messaging;

import java.lang.System;

/**
 * Adapter for the messages list.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00112\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0004\u0011\u0012\u0013\u0014B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/degoogled/androidauto/ui/messaging/MessagesAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/degoogled/androidauto/data/model/Message;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "()V", "timeFormat", "Ljava/text/SimpleDateFormat;", "getItemViewType", "", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "MessageDiffCallback", "ReceivedMessageViewHolder", "SentMessageViewHolder", "app_release"})
public final class MessagesAdapter extends androidx.recyclerview.widget.ListAdapter<com.degoogled.androidauto.data.model.Message, androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    private final java.text.SimpleDateFormat timeFormat = null;
    @org.jetbrains.annotations.NotNull
    public static final com.degoogled.androidauto.ui.messaging.MessagesAdapter.Companion Companion = null;
    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;
    
    public MessagesAdapter() {
        super(null);
    }
    
    @java.lang.Override
    public int getItemViewType(int position) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/degoogled/androidauto/ui/messaging/MessagesAdapter$SentMessageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/degoogled/androidauto/databinding/ItemMessageBinding;", "(Lcom/degoogled/androidauto/ui/messaging/MessagesAdapter;Lcom/degoogled/androidauto/databinding/ItemMessageBinding;)V", "bind", "", "message", "Lcom/degoogled/androidauto/data/model/Message;", "app_release"})
    public final class SentMessageViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final com.degoogled.androidauto.databinding.ItemMessageBinding binding = null;
        
        public SentMessageViewHolder(@org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.databinding.ItemMessageBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.data.model.Message message) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/degoogled/androidauto/ui/messaging/MessagesAdapter$ReceivedMessageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/degoogled/androidauto/databinding/ItemMessageReceivedBinding;", "(Lcom/degoogled/androidauto/ui/messaging/MessagesAdapter;Lcom/degoogled/androidauto/databinding/ItemMessageReceivedBinding;)V", "bind", "", "message", "Lcom/degoogled/androidauto/data/model/Message;", "app_release"})
    public final class ReceivedMessageViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final com.degoogled.androidauto.databinding.ItemMessageReceivedBinding binding = null;
        
        public ReceivedMessageViewHolder(@org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.databinding.ItemMessageReceivedBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.data.model.Message message) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/degoogled/androidauto/ui/messaging/MessagesAdapter$MessageDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/degoogled/androidauto/data/model/Message;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"})
    static final class MessageDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.degoogled.androidauto.data.model.Message> {
        
        public MessageDiffCallback() {
            super();
        }
        
        @java.lang.Override
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.data.model.Message oldItem, @org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.data.model.Message newItem) {
            return false;
        }
        
        @java.lang.Override
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.data.model.Message oldItem, @org.jetbrains.annotations.NotNull
        com.degoogled.androidauto.data.model.Message newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/degoogled/androidauto/ui/messaging/MessagesAdapter$Companion;", "", "()V", "VIEW_TYPE_RECEIVED", "", "VIEW_TYPE_SENT", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}