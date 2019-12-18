package net.themaven.sportscontrols.dataHelpers

import net.themaven.sportscontrols.model.TimeQuantum

interface ResponseInterface {
    fun success(timeQuantum: TimeQuantum)
    fun failure(error : String)
}