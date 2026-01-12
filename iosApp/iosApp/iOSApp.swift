import SwiftUI
import ComposeApp

@main
struct iOSApp: App {


    init() {
        KoinKt.doInitKoin(appDeclaration: nil)
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}