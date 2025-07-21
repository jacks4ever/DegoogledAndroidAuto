package com.degoogled.androidauto.domain.usecase

import com.degoogled.androidauto.data.repository.RecognitionState
import com.degoogled.androidauto.data.repository.VoiceRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for recognizing speech.
 */
class RecognizeSpeechUseCase(
    private val voiceRepository: VoiceRepository
) {
    fun getRecognitionState(): Flow<RecognitionState> {
        return voiceRepository.getRecognitionState()
    }
    
    suspend fun startListening() {
        voiceRepository.startListening()
    }
    
    suspend fun stopListening() {
        voiceRepository.stopListening()
    }
}