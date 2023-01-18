![develop](https://github.com/karthyks/Composed/actions/workflows/develop-workflow.yml/badge.svg) ![release](https://img.shields.io/github/v/release/karthyks/Composed?display_name=tag)

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
https://user-images.githubusercontent.com/4476587/213263411-1450e338-d704-4dea-ae89-07c1d6e65825.mp4
