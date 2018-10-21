package dicoding.tech.metamorph.footballmatchschedule.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*


class FavoriteDBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx,
        "FavoriteFootball.db", null) {

    companion object {
        private var instance: FavoriteDBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FavoriteDBHelper {
            if (instance == null) {
                instance = FavoriteDBHelper(ctx.applicationContext)

            }
            return instance as FavoriteDBHelper
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteDBParam.TABLE_FAVORITE, true,
                FavoriteDBParam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteDBParam.EVENT_ID to TEXT + UNIQUE,
                FavoriteDBParam.EVENT_NAME to TEXT,
                FavoriteDBParam.EVENT_DATE to TEXT,
                FavoriteDBParam.HOME_TEAM_ID to TEXT + UNIQUE,
                FavoriteDBParam.HOME_TEAM_NAME to TEXT,
                FavoriteDBParam.HOME_TEAM_SCORE to TEXT,
                FavoriteDBParam.AWAY_TEAM_ID to TEXT + UNIQUE,
                FavoriteDBParam.AWAY_TEAM_NAME to TEXT,
                FavoriteDBParam.AWAY_TEAM_SCORE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteDBParam.TABLE_FAVORITE, true)
    }


}