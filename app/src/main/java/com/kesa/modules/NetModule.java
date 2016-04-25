package com.kesa.modules;

import android.content.SharedPreferences;
import android.content.res.Resources;

import com.firebase.client.Firebase;
import com.kesa.account.AccountManager;
import com.kesa.account.AccountManagerFireBaseImpl;
import com.kesa.profile.UserManager;
import com.kesa.profile.UserManagerFirebaseImpl;
import com.kesa.util.ImageEncoder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * A module containing the provide methods for dependency graph.
 *
 * @author hongil@
 */
@Module
public class NetModule {
    @Provides
    @Singleton
    UserManager provideProfileManager(Firebase firebase, Resources resources) {
        return new UserManagerFirebaseImpl(firebase, resources);
    }

    @Provides
    @Singleton
    AccountManager provideAccountManager(
        Resources resources,
        Firebase firebase,
        SharedPreferences sharedPreferences) {
        return new AccountManagerFireBaseImpl(resources, firebase, sharedPreferences);
    }

    @Provides
    @Singleton
    ImageEncoder provideImageEncoder() {
        return new ImageEncoder();
    }
}
