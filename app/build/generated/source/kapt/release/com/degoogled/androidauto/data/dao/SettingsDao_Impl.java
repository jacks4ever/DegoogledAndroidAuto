package com.degoogled.androidauto.data.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.degoogled.androidauto.data.model.Setting;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SettingsDao_Impl implements SettingsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Setting> __insertionAdapterOfSetting;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSetting;

  public SettingsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSetting = new EntityInsertionAdapter<Setting>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `settings` (`key`,`value`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Setting value) {
        if (value.getKey() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getKey());
        }
        if (value.getValue() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getValue());
        }
      }
    };
    this.__preparedStmtOfDeleteSetting = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM settings WHERE `key` = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertSetting(final Setting setting,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSetting.insert(setting);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteSetting(final String key, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSetting.acquire();
        int _argIndex = 1;
        if (key == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, key);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteSetting.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<Setting> getSetting(final String key) {
    final String _sql = "SELECT * FROM settings WHERE `key` = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (key == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, key);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[]{"settings"}, new Callable<Setting>() {
      @Override
      public Setting call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final Setting _result;
          if(_cursor.moveToFirst()) {
            final String _tmpKey;
            if (_cursor.isNull(_cursorIndexOfKey)) {
              _tmpKey = null;
            } else {
              _tmpKey = _cursor.getString(_cursorIndexOfKey);
            }
            final String _tmpValue;
            if (_cursor.isNull(_cursorIndexOfValue)) {
              _tmpValue = null;
            } else {
              _tmpValue = _cursor.getString(_cursorIndexOfValue);
            }
            _result = new Setting(_tmpKey,_tmpValue);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Setting>> getAllSettings() {
    final String _sql = "SELECT * FROM settings";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"settings"}, new Callable<List<Setting>>() {
      @Override
      public List<Setting> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final List<Setting> _result = new ArrayList<Setting>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Setting _item;
            final String _tmpKey;
            if (_cursor.isNull(_cursorIndexOfKey)) {
              _tmpKey = null;
            } else {
              _tmpKey = _cursor.getString(_cursorIndexOfKey);
            }
            final String _tmpValue;
            if (_cursor.isNull(_cursorIndexOfValue)) {
              _tmpValue = null;
            } else {
              _tmpValue = _cursor.getString(_cursorIndexOfValue);
            }
            _item = new Setting(_tmpKey,_tmpValue);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
