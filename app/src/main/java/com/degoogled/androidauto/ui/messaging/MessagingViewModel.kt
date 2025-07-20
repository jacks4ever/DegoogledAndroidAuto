package com.degoogled.androidauto.ui.messaging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.degoogled.androidauto.data.model.Conversation
import com.degoogled.androidauto.domain.usecase.GetConversationsUseCase

/**
 * ViewModel for the messaging screen.
 */
class MessagingViewModel(
    getConversationsUseCase: GetConversationsUseCase
) : ViewModel() {

    val conversations: LiveData<List<Conversation>> = getConversationsUseCase().asLiveData()
    
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
}