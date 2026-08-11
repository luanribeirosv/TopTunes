# TopTunes

TopTunes is a Kotlin Multiplatform app (Android + iOS) that browses the iTunes "Top Albums" chart, built with Compose Multiplatform for a fully shared UI.

## Project Summary

The app fetches the iTunes Top Albums RSS feed and shows it as a ranked scrollable list, showing cover art, rank number, name, and artist for each album. Tapping an album opens a full screen detail page with a larger cover, artist, category, release date, and price.

## Core Components

The project is split into six Gradle modules:

- **androidApp** — Android entry point.
- **iosApp** — iOS entry point.
- **shared** — cross-platform app root (`App()`), wires the theme and navigation together.
- **presentation** — Compose UI layer: the album list and detail screens, `AlbumListViewModel`, the `theme` package (colors, typography, shapes), Navigation3 based routing, and the Koin module for UI layer.
- **domain** — pure Kotlin: the `Album`/`AlbumImage` models, the `AlbumService` interface, and the `Result`/`DataError` types used for error handling across layers.
- **data** — the Ktor HTTP client setup, DTOs mapping the iTunes RSS JSON shape, and `KtorAlbumService`, the concrete `AlbumService` implementation.

## Key Features

- Dark-first design system with a violet/gold accent palette.
- Chart style rank numbers (#1, #2, #3…) on the album list.
- Full screen album detail page.
- Centered loading state and a dedicated empty state when there are no albums.
- Network/API errors surfaced to the user via a snackbar.

## Tech Stack

- **Kotlin** — the language the entire codebase is written in, shared across Android and iOS.
- **Compose Multiplatform / Material3** — the shared declarative UI toolkit used to build every screen once for both platforms.
- **Ktor Client** — the HTTP client used to fetch the iTunes Top Albums feed.
- **Koin** — dependency injection, wiring the ViewModel, network client, and services together.
- **Coil** — image loading for album cover art.
- **Navigation3** — back-stack based navigation between the album list and detail screens.
- **Kermit** — cross-platform logging.

## Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and options:

- Android app: `./gradlew :androidApp:assembleDebug`
- iOS app: open the [/iosApp](./iosApp) directory in Xcode and run it from there.

## Testing

Unit tests currently cover the networking/mapping layer and the date-formatting utility:

- `data/src/commonTest/kotlin/com/toptunes/data/service/KtorAlbumServiceTest.kt` — exercises `KtorAlbumService` against a mocked Ktor engine (success, server errors, empty feed).
- `presentation/src/commonTest/kotlin/com/toptunes/presentation/util/DateUtilTest.kt` — covers release-date formatting edge cases.

Run them with:

```
./gradlew :data:allTests :presentation:allTests
```

---
