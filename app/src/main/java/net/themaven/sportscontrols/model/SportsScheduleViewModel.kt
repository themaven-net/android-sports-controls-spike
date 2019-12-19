package net.themaven.sportscontrols.model

import net.themaven.sportscontrols.dataHelpers.ResponseInterface
import net.themaven.sportscontrols.dataHelpers.SportType
import net.themaven.sportscontrols.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class SportsScheduleViewModel(val sportType : SportType) {

    var cachedQuanta = HashMap<String, TimeQuantum>()
    var keys = ArrayList<String>()

    init {
        val quanta = sportType.getSchedule()
        for (i in quanta.indices) {
            keys.add(quanta[i].title)
            cachedQuanta[quanta[i].title] = quanta[i]
        }
    }

    fun timeSortedQuanta() : ArrayList<TimeQuantum> {
        val allValues = ArrayList(cachedQuanta.values)
        val sortedValues = ArrayList(allValues.sortedBy { it.timePeriod.start })
        return sortedValues
    }

    fun quantum(atIndex : Int) : TimeQuantum? {
        return when {
            keys.count() > atIndex -> quantum(keys.get(atIndex))
            else -> null
        }
    }

    fun quantum(forKey : String) : TimeQuantum? {
        val quanta = cachedQuanta[forKey]
        return when {
            quanta != null -> quanta
            else -> null
        }
    }

    fun quantumAsync(forKey : String, handler : ResponseInterface ) {

        val q = quantum(forKey)

        q?.let {
            if (it.realized) {
                handler.success(q)
            } else {

                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                val startDate = simpleDateFormat.format(it.timePeriod.start)
                val endDate = simpleDateFormat.format(it.timePeriod.end)

                val call = ApiClient.getStatsService().getEvents(sportType.sportKey, startDate, endDate)
                call.enqueue(object : Callback<StatsApi> {
                    override fun onResponse(call: Call<StatsApi>, response: Response<StatsApi>) {
                        q.realize(response.body())
                        handler.success(q)
                    }

                    override fun onFailure(call: Call<StatsApi>, t: Throwable) {
                        handler.failure(t.toString());
                    }
                })
            }
        }
    }

}


