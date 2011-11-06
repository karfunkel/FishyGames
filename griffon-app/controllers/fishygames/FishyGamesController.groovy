package fishygames

import griffon.transform.Threading

class FishyGamesController {
    // these will be injected by Griffon
    def model
    def view
    def imageService

    @Threading(Threading.Policy.SKIP)
    def createList = {int amount ->
        List prenames = getClass().classLoader.getResourceAsStream('vornamen.txt').readLines()
        List surenames = getClass().classLoader.getResourceAsStream('nachnamen.txt').readLines()
        List data = []
        amount.times { data << [prenames[(int) (Math.random() * 100)], surenames[(int) (Math.random() * 100)], (int) (Math.random() * 30000 + 10000)] }
        data.sort {it[2] * -1}
        Vector result = []
        data.eachWithIndex { date, idx ->
            result << "${sprintf('%3d', idx + 1)}.   ${date[0]} ${date[1]} - ${sprintf('%,d', date[2])} Punkte"
        }
        result as Vector
    }

}
