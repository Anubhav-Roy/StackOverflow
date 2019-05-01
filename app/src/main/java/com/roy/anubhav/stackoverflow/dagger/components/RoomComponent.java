package com.roy.anubhav.stackoverflow.dagger.components;

import com.roy.anubhav.stackoverflow.dagger.modules.RoomModule;
import com.roy.anubhav.stackoverflow.room.SavedQuestionsDAO;
import com.roy.anubhav.stackoverflow.room.SavedQuestionsDB;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RoomModule.class)
public interface RoomComponent {

    SavedQuestionsDB savedQuestionsDB();

    SavedQuestionsDAO savedQuestionDAO();

}
