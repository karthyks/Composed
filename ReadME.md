Latest version: `0.2.0`

# Composed
Utility library for Jetpack compose projects.

## Overlay
Render dynamic jetpack compose overlay with ease.

## Installation
```
implementation "io.github.karthyks:composed:{latest_version}"
```

## Usage
```
class FullScreenDialog : Overlay {
    override val viewHolder: ViewHolder by viewHolder { disposable ->
        RenderFullScreenDialogContent {
            disposable.dispose()
        }
    }
}
```
https://user-images.githubusercontent.com/4476587/212680965-88f80a2c-170e-488b-96ec-19cc24177cda.mp4

