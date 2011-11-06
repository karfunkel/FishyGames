application {
    title = 'FishyGames'
    startupGroups = ['fishyGames']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "fishyGames"
    'fishyGames' {
        model      = 'fishygames.FishyGamesModel'
        view       = 'fishygames.FishyGamesView'
        controller = 'fishygames.FishyGamesController'
    }

}
