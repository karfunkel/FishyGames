package fishygames

import fishygames.ui.FishyImageButtonUI

application(title: 'FishyGames',
        preferredSize: [1024, 768],
        pack: true,
        //location: [50,50],
        locationByPlatform: true,
        iconImage: imageIcon('/griffon-icon-48x48.png').image,
        iconImages: [imageIcon('/griffon-icon-48x48.png').image,
                imageIcon('/griffon-icon-32x32.png').image,
                imageIcon('/griffon-icon-16x16.png').image]) {
    migLayout(layoutConstraints: '', rowConstraints: '[162!]0[grow, fill]12', columnConstraints: '12[200!]10[grow, fill]12')

    label(icon: imageIcon(image: controller.imageService.highlight('Logo.png', color('white'), 20, 10, 10)), constraints: 'top, align center')
    //label(icon: imageIcon(image: controller.imageService.doubleHighlight('Logo.png', color('red'), color('white'), 20, 40, 20, 20)), constraints: 'top, align center')
    label('', constraints: 'grow, wrap')
    panel(constraints: 'grow') {
        migLayout(layoutConstraints: 'wrap 1, gapy 0, fill', rowConstraints: '[fill, grow]', columnConstraints: '[fill, grow]')
        def imageButtonUI = new FishyImageButtonUI()
        toggleButton(
                icon: imageIcon('/Play.png'),
                toolTipText: 'Highscore',
                ui: imageButtonUI,
                selected: true,
                //constraints: 'grow'
        )
        toggleButton(
                icon: imageIcon('/Chat.png'),
                toolTipText: 'Chat',
                ui: imageButtonUI,
                //constraints: 'grow'
        )
        toggleButton(
                icon: imageIcon('/Mail.png'),
                toolTipText: 'Nachrichten',
                ui: imageButtonUI,
                //constraints: 'grow'
        )
        toggleButton(
                icon: imageIcon('/Profile.png'),
                toolTipText: 'Profil',
                ui: imageButtonUI,
                enabled: false,
                //constraints: 'grow'
        )
        toggleButton(
                icon: imageIcon('/Feedback.png'),
                toolTipText: 'Feedback',
                ui: imageButtonUI,
                enabled: false,
                selected: true,
                //constraints: 'grow'
        )
        toggleButton(
                icon: imageIcon('/Home.png'),
                toolTipText: 'Mein Bereich',
                ui: imageButtonUI,
                //constraints: 'grow'
        )
    }
    // panel(ui: new FishyPanelUI()) {
    // panel(constraints: 'grow', border: new FishyBorder(3, true)) {
    panel(constraints: 'grow') {
        migLayout(layoutConstraints: '', rowConstraints: '[][fill, grow]', columnConstraints: '[grow][200]')
        def x = label('Suche:', constraints: 'right')
        textField(constraints: 'left, grow, wrap')
        scrollPane(constraints: 'spanx 2, grow') {
            list(id: 'mylist')
            mylist.listData = controller.createList(20)
        }
    }
}

