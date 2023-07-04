# Changelog

## [202302.1.0] - Unreleased

### Changed
- Upgrade to Java 17
- Upgrade to 2023.02-ext1

## [202302.0.0] - 2023-03-03

### Removed
- Workflows & Camunda service

### Changed
- Upgrade to 2023.02
- Upgrade to Nodejs 18 & Npm 8
- Update Jenkins configuration to new a12-jenkins
- Use jsonRpc for importing data on startup instead of deprecated document import

## [202206.4.0] - 2023-01-05

### Changed
- Upgrade to 2022.06-ext5

## [202206.3.0] - 2022-12-20

### Changed
- Use Keycloak as default auth provider
- New service ports:
  - Project Template Server: ~~9090~~ -> 8082
  - Postgres: ~~5433~~ -> 8083


## [202206.2.2] - 2022-10-28

### Changed
- Remove palantir git plugin

## [202206.2.1] - 2022-10-28

### Changed
- Upgrade DS version to 34.2.1

## [202206.2.0] - 2022-10-26

### Changed
- Upgrade to 2022.06-ext4
- Update a12 dependencies
- Update models
- Update Typescript ^4.7.4
- Update Webpack ^5.74.0

## [202206.1.0] - 2022-08-05

### Changed
- Upgrade to 2022.06-ext1

## [202206.0.0] - 2022-06-29

### Added
- PT is now deployed and distributed as an artifact `@com.mgmtp.a12.projecttemplate/project-template`

### Changed
- Versioning follows sematic versioning
- Update a12 dependencies (2022.06)
- Replace stylus (Widgets now supports styled-components)
- Update Localizations

### Security
- Fix npm audit

### Removed
- Remove utils-virtual-bom from gradle builds

## [2022.02] - 2022-03-01

### Changed

- Update a12 dependencies (2022.02)
- Update to React 17

## [2021.06-ext6] - 2022-02-03

### Changed

- Update Typescript ^4.5.5
- Update Webpack ^5.56
- Update a12 dependencies (2021.06-ext6)
- Update non-a12 dependencies
- Rename models
- Remove gradle wrapper
- Change fronted port 5000 -> **8081**

### Fixed

- Permission for MainMenu

### Security

- Fix npm audit

## [2021.06-ext3] - 2021-11-08

### Changed

- Upgrade to 2021.06-ext3
- Update A12 Devtools

### Added

- Client tests
- A12 responsive behavior context

### Fixed

- Appmodel locales
- Document import

## [2021.06-ext1] - 2021-09-20

### Changed

- Upgrade to 2021.06
- Change module order
- Use ranges for a12 dependencies
- Initial activity from model
- Split webpack configuration

### Added

- Use A12 prettier

### Fixed

- Workflows Select process view issue
- Relaod leads to new login

## [2021.02] - 2021-06-23

### Added

- Builder instead of dockerfiles for template
- Initial setup and configurations
