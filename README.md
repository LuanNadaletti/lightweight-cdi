# **Lightweight CDI Framework**

A lightweight, custom CDI (Contexts and Dependency Injection) framework designed to manage beans, resolve dependencies, and provide a flexible and extensible structure for long-term projects. This framework offers basic dependency injection, scope management (singleton, prototype), and annotation-based configuration.

---

## **Features**

- **Automatic Bean Discovery**:
  - Scans the classpath for beans annotated with `@Injectable`.
  - Resolves dependencies marked with `@Inject` in fields, constructors, or methods.

- **Dependency Injection**:
  - Automatically injects dependencies using reflection.
  - Supports field injection, constructor injection, and method injection.

- **Scope Management**:
  - Provides built-in scopes:
    - `Singleton`: One instance shared across the application.
    - `Prototype`: A new instance created every time.
